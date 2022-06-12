package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.ProxyUtils;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsList;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.Property;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("all")
public class com_example_qlct_model_ItemRealmProxy extends com.example.qlct.model.Item
    implements RealmObjectProxy, com_example_qlct_model_ItemRealmProxyInterface {

    static final class ItemColumnInfo extends ColumnInfo {
        long idIndex;
        long typeIndex;
        long nameIndex;
        long topicIndex;
        long timeIndex;
        long noteIndex;
        long amountIndex;
        long urlIndex;
        long isCheckedIndex;
        long monthIndex;
        long yearIndex;

        ItemColumnInfo(OsSchemaInfo schemaInfo) {
            super(11);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Item");
            this.idIndex = addColumnDetails("id", "id", objectSchemaInfo);
            this.typeIndex = addColumnDetails("type", "type", objectSchemaInfo);
            this.nameIndex = addColumnDetails("name", "name", objectSchemaInfo);
            this.topicIndex = addColumnDetails("topic", "topic", objectSchemaInfo);
            this.timeIndex = addColumnDetails("time", "time", objectSchemaInfo);
            this.noteIndex = addColumnDetails("note", "note", objectSchemaInfo);
            this.amountIndex = addColumnDetails("amount", "amount", objectSchemaInfo);
            this.urlIndex = addColumnDetails("url", "url", objectSchemaInfo);
            this.isCheckedIndex = addColumnDetails("isChecked", "isChecked", objectSchemaInfo);
            this.monthIndex = addColumnDetails("month", "month", objectSchemaInfo);
            this.yearIndex = addColumnDetails("year", "year", objectSchemaInfo);
        }

        ItemColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new ItemColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final ItemColumnInfo src = (ItemColumnInfo) rawSrc;
            final ItemColumnInfo dst = (ItemColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.typeIndex = src.typeIndex;
            dst.nameIndex = src.nameIndex;
            dst.topicIndex = src.topicIndex;
            dst.timeIndex = src.timeIndex;
            dst.noteIndex = src.noteIndex;
            dst.amountIndex = src.amountIndex;
            dst.urlIndex = src.urlIndex;
            dst.isCheckedIndex = src.isCheckedIndex;
            dst.monthIndex = src.monthIndex;
            dst.yearIndex = src.yearIndex;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private ItemColumnInfo columnInfo;
    private ProxyState<com.example.qlct.model.Item> proxyState;

    com_example_qlct_model_ItemRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (ItemColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.example.qlct.model.Item>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.idIndex);
    }

    @Override
    public void realmSet$id(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.idIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.idIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$type() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.typeIndex);
    }

    @Override
    public void realmSet$type(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.typeIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.typeIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$name() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nameIndex);
    }

    @Override
    public void realmSet$name(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.nameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.nameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.nameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.nameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$topic() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.topicIndex);
    }

    @Override
    public void realmSet$topic(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.topicIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.topicIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.topicIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.topicIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$time() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.timeIndex);
    }

    @Override
    public void realmSet$time(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.timeIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.timeIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.timeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.timeIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$note() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.noteIndex);
    }

    @Override
    public void realmSet$note(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.noteIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.noteIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.noteIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.noteIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public long realmGet$amount() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.amountIndex);
    }

    @Override
    public void realmSet$amount(long value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.amountIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.amountIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$url() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.urlIndex);
    }

    @Override
    public void realmSet$url(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.urlIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.urlIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.urlIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.urlIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public boolean realmGet$isChecked() {
        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.isCheckedIndex);
    }

    @Override
    public void realmSet$isChecked(boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.isCheckedIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.isCheckedIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$month() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.monthIndex);
    }

    @Override
    public void realmSet$month(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.monthIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.monthIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$year() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.yearIndex);
    }

    @Override
    public void realmSet$year(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.yearIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.yearIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("Item", 11, 0);
        builder.addPersistedProperty("id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("type", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("topic", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("time", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("note", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("amount", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("url", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("isChecked", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("month", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("year", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ItemColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new ItemColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Item";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Item";
    }

    @SuppressWarnings("cast")
    public static com.example.qlct.model.Item createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.example.qlct.model.Item obj = realm.createObjectInternal(com.example.qlct.model.Item.class, true, excludeFields);

        final com_example_qlct_model_ItemRealmProxyInterface objProxy = (com_example_qlct_model_ItemRealmProxyInterface) obj;
        if (json.has("id")) {
            if (json.isNull("id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
            } else {
                objProxy.realmSet$id((int) json.getInt("id"));
            }
        }
        if (json.has("type")) {
            if (json.isNull("type")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'type' to null.");
            } else {
                objProxy.realmSet$type((int) json.getInt("type"));
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                objProxy.realmSet$name(null);
            } else {
                objProxy.realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("topic")) {
            if (json.isNull("topic")) {
                objProxy.realmSet$topic(null);
            } else {
                objProxy.realmSet$topic((String) json.getString("topic"));
            }
        }
        if (json.has("time")) {
            if (json.isNull("time")) {
                objProxy.realmSet$time(null);
            } else {
                objProxy.realmSet$time((String) json.getString("time"));
            }
        }
        if (json.has("note")) {
            if (json.isNull("note")) {
                objProxy.realmSet$note(null);
            } else {
                objProxy.realmSet$note((String) json.getString("note"));
            }
        }
        if (json.has("amount")) {
            if (json.isNull("amount")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'amount' to null.");
            } else {
                objProxy.realmSet$amount((long) json.getLong("amount"));
            }
        }
        if (json.has("url")) {
            if (json.isNull("url")) {
                objProxy.realmSet$url(null);
            } else {
                objProxy.realmSet$url((String) json.getString("url"));
            }
        }
        if (json.has("isChecked")) {
            if (json.isNull("isChecked")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'isChecked' to null.");
            } else {
                objProxy.realmSet$isChecked((boolean) json.getBoolean("isChecked"));
            }
        }
        if (json.has("month")) {
            if (json.isNull("month")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'month' to null.");
            } else {
                objProxy.realmSet$month((int) json.getInt("month"));
            }
        }
        if (json.has("year")) {
            if (json.isNull("year")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'year' to null.");
            } else {
                objProxy.realmSet$year((int) json.getInt("year"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.qlct.model.Item createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.example.qlct.model.Item obj = new com.example.qlct.model.Item();
        final com_example_qlct_model_ItemRealmProxyInterface objProxy = (com_example_qlct_model_ItemRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
                }
            } else if (name.equals("type")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$type((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'type' to null.");
                }
            } else if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (name.equals("topic")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$topic((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$topic(null);
                }
            } else if (name.equals("time")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$time((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$time(null);
                }
            } else if (name.equals("note")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$note((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$note(null);
                }
            } else if (name.equals("amount")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$amount((long) reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'amount' to null.");
                }
            } else if (name.equals("url")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$url((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$url(null);
                }
            } else if (name.equals("isChecked")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$isChecked((boolean) reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'isChecked' to null.");
                }
            } else if (name.equals("month")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$month((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'month' to null.");
                }
            } else if (name.equals("year")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$year((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'year' to null.");
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    public static com.example.qlct.model.Item copyOrUpdate(Realm realm, com.example.qlct.model.Item object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null) {
            final BaseRealm otherRealm = ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm();
            if (otherRealm.threadId != realm.threadId) {
                throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
            }
            if (otherRealm.getPath().equals(realm.getPath())) {
                return object;
            }
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.example.qlct.model.Item) cachedRealmObject;
        }

        return copy(realm, object, update, cache);
    }

    public static com.example.qlct.model.Item copy(Realm realm, com.example.qlct.model.Item newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.qlct.model.Item) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.example.qlct.model.Item realmObject = realm.createObjectInternal(com.example.qlct.model.Item.class, false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        com_example_qlct_model_ItemRealmProxyInterface realmObjectSource = (com_example_qlct_model_ItemRealmProxyInterface) newObject;
        com_example_qlct_model_ItemRealmProxyInterface realmObjectCopy = (com_example_qlct_model_ItemRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$id(realmObjectSource.realmGet$id());
        realmObjectCopy.realmSet$type(realmObjectSource.realmGet$type());
        realmObjectCopy.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectCopy.realmSet$topic(realmObjectSource.realmGet$topic());
        realmObjectCopy.realmSet$time(realmObjectSource.realmGet$time());
        realmObjectCopy.realmSet$note(realmObjectSource.realmGet$note());
        realmObjectCopy.realmSet$amount(realmObjectSource.realmGet$amount());
        realmObjectCopy.realmSet$url(realmObjectSource.realmGet$url());
        realmObjectCopy.realmSet$isChecked(realmObjectSource.realmGet$isChecked());
        realmObjectCopy.realmSet$month(realmObjectSource.realmGet$month());
        realmObjectCopy.realmSet$year(realmObjectSource.realmGet$year());
        return realmObject;
    }

    public static long insert(Realm realm, com.example.qlct.model.Item object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.example.qlct.model.Item.class);
        long tableNativePtr = table.getNativePtr();
        ItemColumnInfo columnInfo = (ItemColumnInfo) realm.getSchema().getColumnInfo(com.example.qlct.model.Item.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.idIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.typeIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$type(), false);
        String realmGet$name = ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        String realmGet$topic = ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$topic();
        if (realmGet$topic != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.topicIndex, rowIndex, realmGet$topic, false);
        }
        String realmGet$time = ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$time();
        if (realmGet$time != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.timeIndex, rowIndex, realmGet$time, false);
        }
        String realmGet$note = ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$note();
        if (realmGet$note != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.noteIndex, rowIndex, realmGet$note, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.amountIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$amount(), false);
        String realmGet$url = ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$url();
        if (realmGet$url != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.urlIndex, rowIndex, realmGet$url, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isCheckedIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$isChecked(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.monthIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$month(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.yearIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$year(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.qlct.model.Item.class);
        long tableNativePtr = table.getNativePtr();
        ItemColumnInfo columnInfo = (ItemColumnInfo) realm.getSchema().getColumnInfo(com.example.qlct.model.Item.class);
        com.example.qlct.model.Item object = null;
        while (objects.hasNext()) {
            object = (com.example.qlct.model.Item) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.idIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$id(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.typeIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$type(), false);
            String realmGet$name = ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            }
            String realmGet$topic = ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$topic();
            if (realmGet$topic != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.topicIndex, rowIndex, realmGet$topic, false);
            }
            String realmGet$time = ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$time();
            if (realmGet$time != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.timeIndex, rowIndex, realmGet$time, false);
            }
            String realmGet$note = ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$note();
            if (realmGet$note != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.noteIndex, rowIndex, realmGet$note, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.amountIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$amount(), false);
            String realmGet$url = ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$url();
            if (realmGet$url != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.urlIndex, rowIndex, realmGet$url, false);
            }
            Table.nativeSetBoolean(tableNativePtr, columnInfo.isCheckedIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$isChecked(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.monthIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$month(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.yearIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$year(), false);
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.qlct.model.Item object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.example.qlct.model.Item.class);
        long tableNativePtr = table.getNativePtr();
        ItemColumnInfo columnInfo = (ItemColumnInfo) realm.getSchema().getColumnInfo(com.example.qlct.model.Item.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.idIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.typeIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$type(), false);
        String realmGet$name = ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        String realmGet$topic = ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$topic();
        if (realmGet$topic != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.topicIndex, rowIndex, realmGet$topic, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.topicIndex, rowIndex, false);
        }
        String realmGet$time = ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$time();
        if (realmGet$time != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.timeIndex, rowIndex, realmGet$time, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.timeIndex, rowIndex, false);
        }
        String realmGet$note = ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$note();
        if (realmGet$note != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.noteIndex, rowIndex, realmGet$note, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.noteIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.amountIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$amount(), false);
        String realmGet$url = ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$url();
        if (realmGet$url != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.urlIndex, rowIndex, realmGet$url, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.urlIndex, rowIndex, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isCheckedIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$isChecked(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.monthIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$month(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.yearIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$year(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.qlct.model.Item.class);
        long tableNativePtr = table.getNativePtr();
        ItemColumnInfo columnInfo = (ItemColumnInfo) realm.getSchema().getColumnInfo(com.example.qlct.model.Item.class);
        com.example.qlct.model.Item object = null;
        while (objects.hasNext()) {
            object = (com.example.qlct.model.Item) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.idIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$id(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.typeIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$type(), false);
            String realmGet$name = ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
            }
            String realmGet$topic = ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$topic();
            if (realmGet$topic != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.topicIndex, rowIndex, realmGet$topic, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.topicIndex, rowIndex, false);
            }
            String realmGet$time = ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$time();
            if (realmGet$time != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.timeIndex, rowIndex, realmGet$time, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.timeIndex, rowIndex, false);
            }
            String realmGet$note = ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$note();
            if (realmGet$note != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.noteIndex, rowIndex, realmGet$note, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.noteIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.amountIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$amount(), false);
            String realmGet$url = ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$url();
            if (realmGet$url != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.urlIndex, rowIndex, realmGet$url, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.urlIndex, rowIndex, false);
            }
            Table.nativeSetBoolean(tableNativePtr, columnInfo.isCheckedIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$isChecked(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.monthIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$month(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.yearIndex, rowIndex, ((com_example_qlct_model_ItemRealmProxyInterface) object).realmGet$year(), false);
        }
    }

    public static com.example.qlct.model.Item createDetachedCopy(com.example.qlct.model.Item realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.qlct.model.Item unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.example.qlct.model.Item();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.qlct.model.Item) cachedObject.object;
            }
            unmanagedObject = (com.example.qlct.model.Item) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_example_qlct_model_ItemRealmProxyInterface unmanagedCopy = (com_example_qlct_model_ItemRealmProxyInterface) unmanagedObject;
        com_example_qlct_model_ItemRealmProxyInterface realmSource = (com_example_qlct_model_ItemRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$type(realmSource.realmGet$type());
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$topic(realmSource.realmGet$topic());
        unmanagedCopy.realmSet$time(realmSource.realmGet$time());
        unmanagedCopy.realmSet$note(realmSource.realmGet$note());
        unmanagedCopy.realmSet$amount(realmSource.realmGet$amount());
        unmanagedCopy.realmSet$url(realmSource.realmGet$url());
        unmanagedCopy.realmSet$isChecked(realmSource.realmGet$isChecked());
        unmanagedCopy.realmSet$month(realmSource.realmGet$month());
        unmanagedCopy.realmSet$year(realmSource.realmGet$year());

        return unmanagedObject;
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com_example_qlct_model_ItemRealmProxy aItem = (com_example_qlct_model_ItemRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aItem.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aItem.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aItem.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
