package cs.cuw.com.emu8086;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private EditText instructionET;
    private TextView outputTV;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.instructionET = (EditText)this.findViewById(R.id.instructionET);
        this.outputTV = (TextView)this.findViewById(R.id.outoutTV);
    }

    public void emulateButtonPressed(View v)
    {
        this.outputTV.setText(this.instructionET.getText());
    }
}
