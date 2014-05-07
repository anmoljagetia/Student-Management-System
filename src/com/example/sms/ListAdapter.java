package com.example.sms;
/*
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<String> {

	private LayoutInflater mLayoutInflater;

	public ListAdapter(Context context) {
		super(context, R.layout.row_individual);
		mLayoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;

		if (convertView == null) {
			// inflate the view if it is null
			convertView = mLayoutInflater.inflate(R.layout.row_individual, parent,
					false);

			// store our views in a viewholder because findviewbyid is expensive
			viewHolder = new ViewHolder();
			viewHolder.name = (TextView) convertView.findViewById(R.id.name);
			viewHolder.quantity = (TextView) convertView
					.findViewById(R.id.quantity);
			viewHolder.price = (TextView) convertView.findViewById(R.id.price);
			viewHolder.weight = (TextView) convertView
					.findViewById(R.id.weight);
			convertView.setTag(viewHolder);
		} else {
			// we just saved having to do all our lookups :)
			viewHolder = (ViewHolder) convertView.getTag();
		}

		Item item = getItem(position);
		if (item != null) {
			// set the text in our views
			viewHolder.name.setText(item.getName());
			viewHolder.quantity.setText(String.valueOf(item.getQuantity()));
			viewHolder.price.setText(item.getPrice());
			viewHolder.weight.setText(item.getWeight());
		}
		return convertView;
	}

	public void setData(List<String> data) {
		if (data != null) {
			clear();
			// we add each item individually instead of using addAll() for
			// backward compatibility
			for (Item item : data) {
				add(item);
			}
		}
	}

	// this class holds our lookups
	private class ViewHolder {
		private TextView name;
		private TextView quantity;
		private TextView price;
		private TextView weight;
	}
}
*/