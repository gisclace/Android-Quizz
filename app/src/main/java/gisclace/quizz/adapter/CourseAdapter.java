package gisclace.quizz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import gisclace.quizz.Menu1;
import gisclace.quizz.R;

import java.util.List;

public class CourseAdapter extends BaseAdapter {

	private List<Course> listeCourse;
	private LayoutInflater inflater;
	private Context context;

	public void setCourses(List<Course> listeCourse) {
		this.listeCourse = listeCourse;
	}

	public CourseAdapter(Context context, List<Course> listeCourse) {
		this.listeCourse = listeCourse;
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return listeCourse.size();
	}

	@Override
	public Object getItem(int position) {
		return listeCourse.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listeCourse.get(position).getId();
	}

	@Override
	public View getView(final int position, View view, ViewGroup viewGroup) {
		final ViewHolder holder;

		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.layout_item, null);

			holder.tvQuantite = (TextView) view
					.findViewById(R.id.textViewQuantite);
			holder.tvProduit = (TextView) view
					.findViewById(R.id.textViewProduit);
			holder.cbAchete = (CheckBox) view.findViewById(R.id.checkBoxAchete);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		holder.tvQuantite.setText(listeCourse.get(position).getQuantite() + "");
		holder.tvProduit.setText(listeCourse.get(position).getProduit());
		holder.cbAchete.setChecked(listeCourse.get(position).isAchete());

		// Clic sur la check box
		holder.cbAchete
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						Course course = listeCourse.get(position);
						
						course.setAchete(isChecked);
						
						((Menu1)context).UpdateChecked(course);
					}
				});

		return view;
	}

	private class ViewHolder {
		public TextView tvQuantite;
		public TextView tvProduit;
		public CheckBox cbAchete;
	}

}
