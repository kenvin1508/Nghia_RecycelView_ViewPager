package vn.edu.vtn.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import vn.edu.vtn.hocverecyclerview.MainActivity;
import vn.edu.vtn.hocverecyclerview.R;
import vn.edu.vtn.model.Contact;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    ArrayList<Contact> arrContacts;
    Activity contex;

    public ContactsAdapter(Activity contex, ArrayList<Contact> arrContacts) {
        this.arrContacts = arrContacts;
        this.contex = contex;
    }

    private static OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public Button btnStatus;

        public ViewHolder(final View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            btnStatus = itemView.findViewById(R.id.btnStatus);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(itemView, getLayoutPosition());
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
//        return new ViewHolder(view);
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Contact contact = arrContacts.get(i);
        viewHolder.txtName.setText(contact.getName());
        if (contact.isStatus()) {
            viewHolder.btnStatus.setText("Online");
            viewHolder.btnStatus.setBackgroundColor(ContextCompat.getColor(contex, R.color.colorAccent));

        } else if (contact.isStatus() == false) {
            viewHolder.btnStatus.setText("Offline");
            viewHolder.btnStatus.setBackgroundColor(ContextCompat.getColor(contex, R.color.xam));
        }
    }

    @Override
    public int getItemCount() {
        return arrContacts.size();
    }
}
