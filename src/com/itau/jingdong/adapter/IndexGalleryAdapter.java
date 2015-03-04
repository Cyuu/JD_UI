package com.itau.jingdong.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itau.jingdong.entity.IndexGalleryItemData;
import com.nostra13.universalimageloader.core.ImageLoader;

public class IndexGalleryAdapter extends BaseAdapter {

	private Context context;
	private int layoutId;
	private int to[];
	private List<IndexGalleryItemData> listData;

	public IndexGalleryAdapter(Context context, int layoutId,
			List<IndexGalleryItemData> listData, int to[]) {
		this.context = context;
		this.layoutId = layoutId;
		this.listData = listData;
		this.to = to;
	}

	@Override
	public int getCount() {

		return listData.size() == 0 ? 0 : listData.size();
	}

	@Override
	public Object getItem(int position) {

		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder;
		final int pos = position;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(layoutId, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView) convertView.findViewById(to[0]);
			viewHolder.textView = (TextView) convertView.findViewById(to[1]);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
			resetViewHolder(viewHolder);
		}

		ImageLoader.getInstance().displayImage(listData.get(pos).getImageUrl(),
				viewHolder.imageView);

		viewHolder.textView.setText(listData.get(pos).getPrice());

		return convertView;
	}

	static class ViewHolder {
		ImageView imageView;
		TextView textView;
	}

	protected void resetViewHolder(ViewHolder viewHolder) {
		viewHolder.imageView.setImageBitmap(null);
		viewHolder.textView.setText("");
	}
}
