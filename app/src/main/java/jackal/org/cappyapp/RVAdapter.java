package jackal.org.cappyapp;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static jackal.org.cappyapp.R.color.colorGreen;
import static jackal.org.cappyapp.R.color.colorRed;
import static jackal.org.cappyapp.R.color.colorYellow;

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
        TextView status;
        holdViewHolder(View itemView) {
            super(itemView);
            rv = (RecyclerView)itemView.findViewById(R.id.sectionHolds);
            name = (TextView)itemView.findViewById(R.id.holdName);
            quantity = (TextView)itemView.findViewById(R.id.holdQuantity);
            item = (TextView)itemView.findViewById(R.id.holdItem);
            status = (TextView)itemView.findViewById(R.id.holdStatus);
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

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(holdViewHolder personViewHolder, int i) {
        personViewHolder.name.setText(holds.get(i).name);
        personViewHolder.quantity.setText(holds.get(i).quantity);
        personViewHolder.item.setText(holds.get(i).itemName);
        Integer stat = holds.get(i).getApproved();
        if(stat.equals(1)){
            personViewHolder.status.setBackgroundResource(R.color.colorGreen);
        }else if(stat.equals(2)){
            personViewHolder.status.setBackgroundResource(R.color.colorYellow);
        }else{
            personViewHolder.status.setBackgroundResource(R.color.colorRed);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}