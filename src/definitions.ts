/**
 * @fileoverview NFCUtils definitions - NFC plugin for Capacitor
 * @license MIT
 *
 * @version 1.0
 * @author Andrea Sandrin
 */

import { PluginListenerHandle } from "@capacitor/core";

export interface NFCScannedEvent {
   data: string,
   tagInfo: {
      id: string,
      type: string
   }
}
export interface ScanErrorEvent extends Error {}

export interface NFCUtilsPlugin {
	/**
   * Add a listener to a NFC reading event
   * @param event
   * @param callback
   */
   addListener(eventName: 'nfcScanned', listenerFunc: (event: NFCScannedEvent) => void): Promise<PluginListenerHandle>;
	
   /**
   * Add a listener to a NFC reading event
   * @param event
   * @param callback
   */
   addListener(eventName: 'scanError', listenerFunc: (event: ScanErrorEvent) => void): Promise<PluginListenerHandle>;
  
   /**
    * Start listening for NFC read
    */
   startReading(): Promise<void>;
  
   /**
    * Stop listening for NFC read
    */
   stopReading(): Promise<void>;

   /**
    * Removes all listeners.
    */
   removeAllListeners(): Promise<void>;
}