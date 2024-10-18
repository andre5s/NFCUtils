package cloud.sandrin.plugins.nfcutils;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.util.Log;
import android.nfc.Tag;
import androidx.appcompat.app.AppCompatActivity;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

/**
 * NFC plugin for Capacitor
 * @license MIT
 *
 * @version 1.1
 * @date Oct 2024
 * @author Andrea Sandrin
 */
@CapacitorPlugin(name = "NFCUtils")
public class NFCUtilsPlugin extends Plugin {
  private AppCompatActivity activity;
  private NfcAdapter nfcAdapter;
  private PendingIntent pendingIntent;
  private IntentFilter[] intentFiltersArray;
  private String[][] techListsArray;
  private NFCUtils nfcUtils;
  private boolean readingEnabled = false;
  private boolean nfcSupported;

  @Override
  public void load(){
    activity = getActivity();
    nfcUtils = new NFCUtils();
    nfcAdapter = NfcAdapter.getDefaultAdapter(activity);
    // Check if NFC is supported
    if(nfcAdapter != null){
      nfcSupported = true;
    }
    else{
      nfcSupported = false;
      Log.e("NFCUtilsPlugin", "NFC service not supported on this device - no adapter exists");
      return;
    }

    pendingIntent = PendingIntent.getActivity(
      activity,
      0,
      new Intent(activity, activity.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
      PendingIntent.FLAG_MUTABLE
    );
    // Setup an intent filter for all MIME based dispatches
    IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED); // NDEF
    try {
      ndef.addDataType("*/*");
    } catch (IntentFilter.MalformedMimeTypeException e) {
      throw new RuntimeException("fail", e);
    }
    IntentFilter td = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED); // MifareUltralight
    intentFiltersArray = new IntentFilter[] {ndef, td};
    // Setup a tech list for all tag types
    techListsArray = new String[][] {new String[] {
      NfcV.class.getName(),
      NfcF.class.getName(),
      NfcA.class.getName(),
      NfcB.class.getName()
    }};
  }

  /**
   * Start handling NFC
   * @param call
   */
  @PluginMethod()
  public void startReading(PluginCall call) {
    readingEnabled = true;
    call.resolve();
  }

  /**
   * Stop handling NFC
   * @param call
   */
  @PluginMethod()
  public void stopReading(PluginCall call) {
    readingEnabled = false;
    call.resolve();
  }

  /**
   * Check if NFC is supported
   * @param call
   * @since 1.1
   */
  @PluginMethod()
  public void isSupported(PluginCall call) {
    call.resolve(new JSObject().put("supported", nfcSupported));
  }

  @Override
  protected void handleOnPause() {
    super.handleOnPause();
    if(nfcSupported) nfcAdapter.disableForegroundDispatch(activity);
  }

  @Override
  protected void handleOnResume() {
    super.handleOnResume();
    if(nfcSupported) nfcAdapter.enableForegroundDispatch(activity, pendingIntent, intentFiltersArray, techListsArray);
  }

  /**
   * Listen for new NFC tag intent
   * @param intent
   */
  @Override
  protected void handleOnNewIntent(Intent intent) {
    super.handleOnNewIntent(intent);
    if(nfcSupported && readingEnabled){
      // Check for intent action type
      // NDEF
      if(NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())){
        Log.d("NFC-Debug", "ACTION_NDEF_DISCOVERED");
        // Get tag
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        // Read tag
        JSObject content = nfcUtils.readTagNDEF(tagFromIntent);

        if(content == null){
          JSObject scanErrorEvent = new JSObject();
          scanErrorEvent.put("name", "Exception");
          scanErrorEvent.put("message", "Error reading NDEF tag");
          notifyListeners("scanError", scanErrorEvent, true);
        }
        else{
          notifyListeners("nfcScanned", content, true);
        }
      }
      // MifareUltralight
      else if(NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
        Log.d("NFC-Debug", "ACTION_TAG_DISCOVERED");
        // Get tag
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        // Read tag
        JSObject content = nfcUtils.readTagMU(tagFromIntent);

        if(content == null){
          JSObject scanErrorEvent = new JSObject();
          scanErrorEvent.put("name", "IOException");
          scanErrorEvent.put("message", "Error reading MifareUltralight tag");
          notifyListeners("scanError", scanErrorEvent, true);
        }
        else{
          notifyListeners("nfcScanned", content, true);
        }
      }
    }
  }
}
