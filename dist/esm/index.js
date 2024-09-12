/**
 * @fileoverview NFC plugin for Capacitor
 * @license MIT
 *
 * @version 1.0
 * @author Andrea Sandrin
 */
import { registerPlugin } from '@capacitor/core';
const NFCUtils = registerPlugin('NFCUtils', {
    web: () => import('./web').then(m => new m.NFCUtilsWeb()),
});
export * from './definitions';
export { NFCUtils };
//# sourceMappingURL=index.js.map