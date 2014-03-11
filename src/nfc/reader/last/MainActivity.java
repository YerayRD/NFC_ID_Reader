package nfc.reader.last;

import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static final String TAG = "NfcDemo";
    private TextView mTextView;
    private NfcAdapter mNfcAdapter;
    PendingIntent mPendingIntent;
    private NFC nfcTag = new NFC();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textView_explanation);
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null) {
            // Stop here, we definitely need NFC
            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        if (!mNfcAdapter.isEnabled()) {
        	Toast.makeText(this, "NFC is disabled", Toast.LENGTH_LONG).show();
        } else {
        	Toast.makeText(this, "NFC is enabled", Toast.LENGTH_LONG).show();
        }
        mPendingIntent = PendingIntent.getActivity(this, 0,new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        String action = getIntent().getAction();
        Log.d(TAG,"intent to string;" + getIntent().toString());
        Log.d(TAG, "action: " + getIntent().getAction());
        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action))
        	handleIntent(getIntent());
        
    }
    @Override
    public void onResume() {
        super.onResume();
        mNfcAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
    }

    @Override
    public void onNewIntent(Intent intent) {
        Log.i("Foreground dispatch", "Discovered tag with intent: " + intent);
        handleIntent(intent);
        
    }
    private void handleIntent(Intent intent) {
    	  Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);   	  							      
    	  nfcTag.set_NFC_Tag_ID(tag.getId()); //getID() returns a byte array which is handle in the class
    	  Toast.makeText(getApplicationContext(), "UID: " + nfcTag.get_NFC_Tag_ID(),    Toast.LENGTH_SHORT).show();
    	  mTextView = (TextView) findViewById(R.id.textView_explanation);
    	  mTextView.setText("NFC tag has been read, UID: " + nfcTag.get_NFC_Tag_ID());
    	   
    }

    @Override
    public void onPause() {
        super.onPause();
        mNfcAdapter.disableForegroundDispatch(this);
    }
    
 
    	
}
