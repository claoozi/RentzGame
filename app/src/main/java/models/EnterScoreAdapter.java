package models;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import claudia.rent.R;

/**
 * Created by ei047234 on 4/18/17.
 */

public class EnterScoreAdapter extends ArrayAdapter<Player> {
    private final Context context;
    private final ArrayList<Player> data;
    private final int layoutResourceId;

    public EnterScoreAdapter(Context context, int layoutResourceId, ArrayList<Player> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        EnterScoreAdapter.ViewHolder holder;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new EnterScoreAdapter.ViewHolder();
            holder.playerTextView = (TextView)row.findViewById(R.id.textViewPlayer);
            holder.totalScoreTextView = (TextView)row.findViewById(R.id.textViewTotal);
            holder.scoreEditText = (EditText) row.findViewById(R.id.editTextScore);

            holder.scoreEditText.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) { }

                @Override
                public void afterTextChanged(Editable s) {
                    long tempScore = s.toString().isEmpty() ? 0 : Integer.parseInt(s.toString());
                    Player p = data.get(position);
                    p.setTempScore(tempScore);

                    notifyDataSetChanged();
                }
            });

            row.setTag(holder);
        }
        else
        {
            holder = (EnterScoreAdapter.ViewHolder)row.getTag();
        }

        Player p = data.get(position);
        holder.playerTextView.setText(p.getName());
        holder.scoreEditText.setText(String.valueOf(p.getTempScore()));
        holder.scoreEditText.setSelection(holder.scoreEditText.getText().length());
        holder.totalScoreTextView.setText(String.valueOf(p.getTempScore() + p.getScore()));
        return row;
    }

    static class ViewHolder
    {
        TextView playerTextView;
        TextView totalScoreTextView;
        EditText scoreEditText;
    }
}
