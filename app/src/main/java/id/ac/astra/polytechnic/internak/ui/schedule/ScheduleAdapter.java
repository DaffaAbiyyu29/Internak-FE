package id.ac.astra.polytechnic.internak.ui.schedule;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.api.ApiService;
import id.ac.astra.polytechnic.internak.model.Schedule;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private List<Schedule> scheduleList;
    private ApiService apiService;

    public ScheduleAdapter(List<Schedule> scheduleList, ApiService apiService) {
        this.scheduleList = scheduleList;
        this.apiService = apiService;
    }

    public void updateData(List<Schedule> schedules) {
        this.scheduleList = schedules;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Schedule schedule = scheduleList.get(position);

        holder.startTime.setText(schedule.getSchDateStart() + " - ");
        holder.endTime.setText(schedule.getSchDateEnd());
        holder.name.setText(schedule.getSchName());
        holder.description.setText(schedule.getSchJenisMakan());

        int strokeColorRes = (position % 2 == 0) ? R.color.colorPrimary : R.color.colorSecondary;
        int strokeColor = holder.itemView.getContext().getResources().getColor(strokeColorRes);
        holder.cardView.setStrokeColor(strokeColor);

        int sideLineColorRes = (position % 2 == 0) ? R.color.colorPrimary : R.color.colorSecondary;
        int sideLineColor = holder.itemView.getContext().getResources().getColor(sideLineColorRes);
        holder.sideLine.setCardBackgroundColor(sideLineColor);

        int imageResId = (position % 2 == 0) ? R.drawable.ic_cube : R.drawable.ic_cube2;
        holder.iconCube.setImageResource(imageResId);
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView startTime;
        TextView endTime;
        TextView name;
        TextView description;
        MaterialCardView cardView;
        MaterialCardView sideLine;
        ImageView iconCube;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            startTime = itemView.findViewById(R.id.datestart_text);
            endTime = itemView.findViewById(R.id.dateend_text);
            name = itemView.findViewById(R.id.schName);
            description = itemView.findViewById(R.id.schDesc);
            cardView = itemView.findViewById(R.id.card_schedule);
            sideLine = itemView.findViewById(R.id.sideLine);
            iconCube = itemView.findViewById(R.id.icon_cube);
        }
    }
}
