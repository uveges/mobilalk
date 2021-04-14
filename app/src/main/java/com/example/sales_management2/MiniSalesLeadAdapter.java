package com.example.sales_management2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MiniSalesLeadAdapter extends RecyclerView.Adapter<MiniSalesLeadAdapter.ViewHolder> implements Filterable {

    private ArrayList<MiniSalesItem> miniSalesItems;
    // ha szurni is akarnank: private ArrayList<MiniSalesItem> miniSalesItems;
    // az eredményeknek!


    private Context context;

    //implements filterable________________________
    @Override
    public Filter getFilter() {
        return miniSalesItemsFilter;
    }

    private Filter miniSalesItemsFilter = new Filter() {

        //kapott input alapján hogyan történjen a szűrés?
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            ArrayList<MiniSalesItem> filteredlist = new ArrayList<MiniSalesItem>();
            FilterResults results = new FilterResults();

            //mi történik a szűrés során?
            if (constraint == null || constraint.length() == 0){        //ha nem szűrünk semmire
                results.count = miniSalesItems.size();
                results.values = miniSalesItems;
            } else {

                System.out.println("keresessss");
                    //egyedi keresés ID-re
                    for (MiniSalesItem item : miniSalesItems){
                        if(item.getId().contains(constraint)){
                            filteredlist.add(item);
                        }
                    }

                results.count = filteredlist.size();
                results.values = filteredlist;
                return results;
            }

            return results;
        }

        //Hogyan adjuk vissza a filterezés eredményét
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            System.out.println("ertesites");

            miniSalesItems = (ArrayList)results.values;
            notifyDataSetChanged();         //recyclerview értesítése a változásokról
        }

        private boolean isNumeric(String strNum){
            if (strNum == null) {
                return false;
            }
            try {
                double d = Integer.parseInt(strNum);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return true;
        }
    };

    //filterable__________________________________

    public MiniSalesLeadAdapter(Context context, ArrayList<MiniSalesItem> miniSalesItems) {
        this.miniSalesItems = miniSalesItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //ez fogja a kis kartyanak megfelelo layout -ot továbbítani annak a kontext-nek, aki meghívta!
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.mini_sales_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MiniSalesLeadAdapter.ViewHolder holder, int position) {
        MiniSalesItem currentItem = miniSalesItems.get(position);
        holder.bindTo(currentItem);
    }

    @Override
    public int getItemCount() {
        return miniSalesItems.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView id;
        private TextView creationDate;
        private TextView salesLeadPriorityType;
        private TextView type;
        private TextView estimatedRevenue;

        public ViewHolder(View salesView) {
            super(salesView);
            id = salesView.findViewById(R.id.LeadID);
            creationDate = salesView.findViewById(R.id.creationDate);
            salesLeadPriorityType = salesView.findViewById(R.id.salesLeadPriority);
            type = salesView.findViewById(R.id.type);
            estimatedRevenue = salesView.findViewById(R.id.estimatedRevenue);

            context = salesView.getContext();

            //ha a kártyán levő gombra kattintunk, akkor ugrunk a megfelelő nézetre, betöltve az éppen nézett elem részleteit
            salesView.findViewById(R.id.reszletek).setOnClickListener(v -> {

                //ekkor megnyitunk egy új "nézetet", aminek átpasszoljuk azt az id-t (v. azonosítót) ami alapján meg kell nyitnia egy darab rekordot!
                Intent intent = new Intent(context, Activity_ItemDetails.class);
                intent.putExtra("ID_toOpen", id.getText().toString());
                context.startActivity(intent);

                Log.d("Activity", "Részletek gomb megnyomva.");
            });

        }

        //A Viewholder-ben összekötött elemek feltöltése adattal
        public void bindTo(MiniSalesItem currentItem) {

            id.setText(currentItem.getId());
            creationDate.setText(currentItem.getCreationDate());
            salesLeadPriorityType.setText(currentItem.getSalesLeadPriority().toString());
            type.setText(currentItem.getType());
            estimatedRevenue.setText(String.valueOf(currentItem.getEstimatedRevenue()));

        };

    };

}

