package fr.iut.androidprojet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.iut.androidprojet.db.Student;

public class StudentAdapter extends ArrayAdapter<Student> {

    public StudentAdapter(Context mCtx, List<Student> taskList) {
        super(mCtx, R.layout.template_card, taskList);
    }

    /**
     * Remplit une ligne de la listView avec les informations de la multiplication associée
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Récupération de la multiplication
        final Student student = getItem(position);

        // Charge le template XML
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.template_card, parent, false);

        // Récupération des objets graphiques dans le template
        TextView textViewLogin = (TextView) rowView.findViewById(R.id.login);
        TextView textViewFullName = (TextView) rowView.findViewById(R.id.full_name);

        //
        textViewLogin.setText(student.getLogin());
        textViewFullName.setText(student.getFullName());

        //
        return rowView;
    }
}
