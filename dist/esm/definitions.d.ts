/**
 * @fileoverview NFCUtils definitions - NFC plugin for Capacitor
 * @license MIT
 *
 * @version 1.0
 * @author Andrea Sandrin
 */
import { PluginListenerHandle } from "@capacitor/core";
/**
 * Scanned NFC contents
 */
export interface NFCScannedEvent {
    data: string;
    tagInfo: {
        id: string;
        type: string;
    };
}
/**
 * Scan error
 */
export interface ScanErrorEvent extends Error {
}
export interface NFCUtilsPlugin {
    /**
   * Listen for a scanned NFC event
   * @param eventName
   * @param listenerFunc
   */
    addListener(eventName: 'nfcScanned', listenerFunc: (event: NFCScannedEvent) => void): Promise<PluginListenerHandle>;
    /**
    * Listen for a scan error event
    * @param eventName
    * @param listenerFunc
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
     * Check if NFC is supported
     */
    isSupported(): Promise<{
        supported: boolean;
    }>;
    /**
     * Removes all listeners.
     */
    removeAllListeners(): Promise<void>;
}
