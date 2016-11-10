package zobi.paulo.kalawi.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import zobi.paulo.kalawi.R;
import zobi.paulo.kalawi.model.Zamel;

public class ZamelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Zamel> mZamelList;

    public ZamelAdapter(List<Zamel> zamelList) {
        mZamelList = zamelList;
        setHasStableIds(true);
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mName;
        private TextView mBirthInfo;

        ItemViewHolder(View view) {
            super(view);

            mName = (TextView) view.findViewById(R.id.name);
            mBirthInfo = (TextView) view.findViewById(R.id.birth_info);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_zamel, parent, false);

        // create ViewHolder
        return new ItemViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemHolder = (ItemViewHolder) holder;
        // zamel
        Zamel zamel = getItem(position);

        // inflate item
        itemHolder.mName.setText(zamel.getName());
        itemHolder.mBirthInfo.setText(zamel.getBirthDate() + ", " + zamel.getBirthPlace());
    }

    public Zamel getItem(int position) {
        return mZamelList.get(position);
    }

    @Override
    public int getItemCount() {
        return mZamelList.size();
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId().hashCode();
    }

    public void updateZamelList(List<Zamel> mZamelList) {
        this.mZamelList = mZamelList;
        notifyDataSetChanged();
    }
}
