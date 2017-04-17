package models;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import java.util.ArrayList;

import claudia.rent.R;

/**
 * Created by Clau on 4/16/2017.
 */

public class GameArrayAdapter extends ArrayAdapter<Game> {

    private final Context context;
    private final ArrayList<Game> data;
    private final int layoutResourceId;

    public GameArrayAdapter(Context context, int layoutResourceId, ArrayList<Game> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = null;

        final Game game = data.get(position);

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.checkBox = (CheckBox) row.findViewById(R.id.checkBoxGame);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        holder.checkBox.setText(game.getGameType().toString());
        holder.checkBox.setChecked(game.isPlayed());

        holder.checkBox.setEnabled(!game.isPlayed());

        return row;
    }

    static class ViewHolder
    {
        CheckBox checkBox;
    }

}
