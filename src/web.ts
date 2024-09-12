/**
 * @fileoverview NFCUtils for web - NFC plugin for Capacitor
 * @license MIT
 *
 * @version 1.0
 * @author Andrea Sandrin
 */

import { WebPlugin } from '@capacitor/core';
import type { NFCUtilsPlugin } from './definitions';

export class NFCUtilsWeb extends WebPlugin implements NFCUtilsPlugin {
  constructor(){
    super();
    throw new Error('Plugin not implemented for Web/PWA');
  }

  startReading(): Promise<void> {
    throw new Error('Method not implemented.');
  }

  stopReading(): Promise<void> {
    throw new Error('Method not implemented.');
  }
}
