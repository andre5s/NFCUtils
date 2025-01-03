var capacitorNFCUtils = (function (exports, core) {
    'use strict';

    /**
     * @fileoverview NFC plugin for Capacitor
     * @license MIT
     *
     * @version 1.0
     * @author Andrea Sandrin
     */
    const NFCUtils = core.registerPlugin('NFCUtils', {
        web: () => Promise.resolve().then(function () { return web; }).then(m => new m.NFCUtilsWeb()),
    });

    /**
     * @fileoverview NFCUtils for web - NFC plugin for Capacitor
     * @license MIT
     *
     * @version 1.0
     * @author Andrea Sandrin
     */
    class NFCUtilsWeb extends core.WebPlugin {
        constructor() {
            super();
            throw new Error('Plugin not implemented for Web/PWA');
        }
    }

    var web = /*#__PURE__*/Object.freeze({
        __proto__: null,
        NFCUtilsWeb: NFCUtilsWeb
    });

    exports.NFCUtils = NFCUtils;

    return exports;

})({}, capacitorExports);
//# sourceMappingURL=plugin.js.map
