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
 * Created by Clau on 4/13/2017.
 */

public class PlayerArrayAdapter extends ArrayAdapter<Player> {

    private final Context context;
    private final ArrayList<Player> data;
    private final int layoutResourceId;

    public PlayerArrayAdapter(Context context, int layoutResourceId, ArrayList<Player> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        final Player player = data.get(position);

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.textView = (TextView)row.findViewById(R.id.textViewPlayer);
            holder.editText = (EditText) row.findViewById(R.id.editPlayerText);

            holder.editText.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // TODO Auto-generated method stub
                    System.out.println("ONtext changed " + s.toString());
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                    // TODO Auto-generated method stub
                    System.out.println("beforeTextChanged " + s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {
                    player.setName(s.toString());
                }
            });

            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)row.getTag();
        }

        holder.textView.setText(player.getPositionString());
        holder.editText.setText(player.getName());
        return row;
    }

    static class ViewHolder
    {
        TextView textView;
        EditText editText;
    }
}
