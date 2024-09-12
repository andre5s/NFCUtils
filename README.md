# nfcutils

Capacitor plugin for NFC functionalities

## Install

```bash
npm install nfcutils
npx cap sync
```

## API

<docgen-index>

* [`addListener('nfcScanned', ...)`](#addlistenernfcscanned-)
* [`addListener('scanError', ...)`](#addlistenerscanerror-)
* [`startReading()`](#startreading)
* [`stopReading()`](#stopreading)
* [`removeAllListeners()`](#removealllisteners)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### addListener('nfcScanned', ...)

```typescript
addListener(eventName: 'nfcScanned', listenerFunc: (event: NFCScannedEvent) => void) => Promise<PluginListenerHandle>
```

Add a listener to a NFC reading event

| Param              | Type                                                                            |
| ------------------ | ------------------------------------------------------------------------------- |
| **`eventName`**    | <code>'nfcScanned'</code>                                                       |
| **`listenerFunc`** | <code>(event: <a href="#nfcscannedevent">NFCScannedEvent</a>) =&gt; void</code> |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt;</code>

--------------------


### addListener('scanError', ...)

```typescript
addListener(eventName: 'scanError', listenerFunc: (event: ScanErrorEvent) => void) => Promise<PluginListenerHandle>
```

Add a listener to a NFC reading event

| Param              | Type                                                                          |
| ------------------ | ----------------------------------------------------------------------------- |
| **`eventName`**    | <code>'scanError'</code>                                                      |
| **`listenerFunc`** | <code>(event: <a href="#scanerrorevent">ScanErrorEvent</a>) =&gt; void</code> |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt;</code>

--------------------


### startReading()

```typescript
startReading() => Promise<void>
```

Start listening for NFC read

--------------------


### stopReading()

```typescript
stopReading() => Promise<void>
```

Stop listening for NFC read

--------------------


### removeAllListeners()

```typescript
removeAllListeners() => Promise<void>
```

Removes all listeners.

--------------------


### Interfaces


#### PluginListenerHandle

| Prop         | Type                                      |
| ------------ | ----------------------------------------- |
| **`remove`** | <code>() =&gt; Promise&lt;void&gt;</code> |


#### NFCScannedEvent

| Prop          | Type                                       |
| ------------- | ------------------------------------------ |
| **`data`**    | <code>string</code>                        |
| **`tagInfo`** | <code>{ id: string; type: string; }</code> |


#### ScanErrorEvent

</docgen-api>
