package cloud.sandrin.plugins.nfcutils;

import android.nfc.Tag;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.util.Log;
import com.getcapacitor.JSObject;
import java.io.IOException;

/**
 * NFC plugin for Capacitor - utils
 * @license MIT
 *
 * @version 1.0
 * @date Sep 2024
 * @author Andrea Sandrin
 */
public class NFCUtils {

  private static final String NDEF_TAG = Ndef.class.getSimpleName();
  private static final String MU_TAG = MifareUltralight.class.getSimpleName();

  /**
   * Read NDEF type tag
   *
   * @param tag
   * @return tag data
   */
  public JSObject readTagNDEF(Tag tag) {
    Ndef ndef = Ndef.get(tag);
    try {
      ndef.connect();
      byte[] id = tag.getId();
      byte[] payload = ndef.getNdefMessage().getRecords()[0].getPayload();

      JSObject nfcScannedEvent = new JSObject();
      // tagInfo
      JSObject tagInfo = new JSObject();
      tagInfo.put("id", bytesToHex(id));
      tagInfo.put("type", "NDEF");
      nfcScannedEvent.put("tagInfo", tagInfo);
      // data
      nfcScannedEvent.put("data", new String(payload));

      return nfcScannedEvent;

    } catch (Exception e) {
      Log.e(NDEF_TAG, "Exception while reading NDEF record...", e);
    } finally {
      if (ndef != null) {
        try {
          ndef.close();
        }
        catch (IOException e) {
          Log.e(NDEF_TAG, "Error closing tag...", e);
        }
      }
    }
    return null;
  }

  /**
   * Read MifareUltralight type tag
   * @param tag
   * @return tag data
   */
  public JSObject readTagMU(Tag tag) {
    MifareUltralight mifare = MifareUltralight.get(tag);
    try {
      mifare.connect();
      byte[] id = tag.getId();
      byte[] payload = mifare.readPages(4);

      JSObject nfcScannedEvent = new JSObject();
      // tagInfo
      JSObject tagInfo = new JSObject();
      tagInfo.put("id", bytesToHex(id));
      tagInfo.put("type", "MifareUltralight");
      nfcScannedEvent.put("tagInfo", tagInfo);
      // data
      nfcScannedEvent.put("data", bytesToHex(payload));

      return nfcScannedEvent;

    } catch (IOException e) {
      Log.e(MU_TAG, "IOException while reading MifareUltralight message...", e);
    } finally {
      if (mifare != null) {
        try {
          mifare.close();
        }
        catch (IOException e) {
          Log.e(MU_TAG, "Error closing tag...", e);
        }
      }
    }
    return null;
  }

  private String bytesToHex(byte[] bytes){
    StringBuilder result = new StringBuilder();
    for (byte b : bytes) {
      result.append(String.format("%02X", b));
    }
    return result.toString();
  }
}
