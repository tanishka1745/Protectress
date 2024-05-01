package com.example.protectress.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.protectress.Adapters.HelpLineAdapter;
import com.example.protectress.Modals.HelpLineModal;
import com.example.protectress.R;

import java.util.ArrayList;
import java.util.List;

public class HelpLineActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HelpLineAdapter helpLineAdapter;
    private List<HelpLineModal> helplines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_line);

        recyclerView=findViewById(R.id.recyclerView_helpline);
        helplines= new ArrayList<>();
        helplines.add(new HelpLineModal(
                getString(R.string.helpline_name1),
                getString(R.string.helpline_num1),
                getString(R.string.helpline_desc1)
        ));
        helplines.add(new HelpLineModal(
                getString(R.string.helpline_name2),
                getString(R.string.helpline_num2),
                getString(R.string.helpline_desc2)
        ));
        helplines.add(new HelpLineModal(
                getString(R.string.helpline_name3),
                getString(R.string.helpline_num3),
                getString(R.string.helpline_desc3)
        ));
        helplines.add(new HelpLineModal(
                getString(R.string.helpline_name4),
                getString(R.string.helpline_num4),
                getString(R.string.helpline_desc4)
        ));
        helplines.add(new HelpLineModal(
                getString(R.string.helpline_name6),
                getString(R.string.helpline_num6),
                getString(R.string.helpline_desc6)
        ));

        helplines.add(new HelpLineModal(
                getString(R.string.helpline_name8),
                getString(R.string.helpline_num8),
                getString(R.string.helpline_desc8)
        ));
        helplines.add(new HelpLineModal(
                getString(R.string.helpline_name9),
                getString(R.string.helpline_num9),
                getString(R.string.helpline_desc9)
        ));

        helplines.add(new HelpLineModal(
                getString(R.string.helpline_name11),
                getString(R.string.helpline_num11),
                getString(R.string.helpline_desc11)
        ));

        helpLineAdapter= new HelpLineAdapter(helplines,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(helpLineAdapter);

    }
}