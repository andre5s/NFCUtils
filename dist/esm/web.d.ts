/**
 * @fileoverview NFCUtils for web - NFC plugin for Capacitor
 * @license MIT
 *
 * @version 1.0
 * @author Andrea Sandrin
 */
import { WebPlugin } from '@capacitor/core';
import type { NFCUtilsPlugin } from './definitions';
export declare class NFCUtilsWeb extends WebPlugin implements NFCUtilsPlugin {
    constructor();
    startReading(): Promise<void>;
    stopReading(): Promise<void>;
}
