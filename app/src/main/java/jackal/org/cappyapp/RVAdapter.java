package jackal.org.cappyapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jholle42 on 6/20/18.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.holdViewHolder>{

    List<hold> holds;

    RVAdapter(List<hold> holds){
        this.holds = holds;
    }


    public static class holdViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rv;
        TextView name;
        TextView quantity;
        TextView item;
        holdViewHolder(View itemView) {
            super(itemView);
            rv = (RecyclerView)itemView.findViewById(R.id.sectionHolds);
            name = (TextView)itemView.findViewById(R.id.holdName);
            quantity = (TextView)itemView.findViewById(R.id.holdQuantity);
            item = (TextView)itemView.findViewById(R.id.holdItem);
        }
    }

    @Override
    public holdViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hold, viewGroup, false);
        holdViewHolder pvh = new holdViewHolder(v);
        return pvh;
    }

    @Override
    public int getItemCount() {
        return holds.size();
    }

    @Override
    public void onBindViewHolder(holdViewHolder personViewHolder, int i) {
        personViewHolder.name.setText(holds.get(i).name);
        personViewHolder.quantity.setText(holds.get(i).quantity);
        personViewHolder.item.setText(holds.get(i).itemName);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}