package gnu.trove.map.hash;

import gnu.trove.TLongCollection;
import gnu.trove.TShortCollection;
import gnu.trove.function.TLongFunction;
import gnu.trove.impl.HashFunctions;
import gnu.trove.impl.hash.THashPrimitiveIterator;
import gnu.trove.impl.hash.TPrimitiveHash;
import gnu.trove.impl.hash.TShortLongHash;
import gnu.trove.iterator.TLongIterator;
import gnu.trove.iterator.TShortIterator;
import gnu.trove.iterator.TShortLongIterator;
import gnu.trove.map.TShortLongMap;
import gnu.trove.procedure.TLongProcedure;
import gnu.trove.procedure.TShortLongProcedure;
import gnu.trove.procedure.TShortProcedure;
import gnu.trove.set.TShortSet;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class TShortLongHashMap extends TShortLongHash implements TShortLongMap, Externalizable {
   static final long serialVersionUID = 1L;
   protected transient long[] _values;

   public TShortLongHashMap() {
   }

   public TShortLongHashMap(int var1) {
      super(var1);
   }

   public TShortLongHashMap(int var1, float var2) {
      super(var1, var2);
   }

   public TShortLongHashMap(int var1, float var2, short var3, long var4) {
      super(var1, var2, var3, var4);
   }

   public TShortLongHashMap(short[] var1, long[] var2) {
      super(Math.max(var1.length, var2.length));
      int var3 = Math.min(var1.length, var2.length);

      for(int var4 = 0; var4 < var3; ++var4) {
         this.put(var1[var4], var2[var4]);
      }

   }

   public TShortLongHashMap(TShortLongMap var1) {
      super(var1.size());
      if (var1 instanceof TShortLongHashMap) {
         TShortLongHashMap var2 = (TShortLongHashMap)var1;
         this._loadFactor = var2._loadFactor;
         this.no_entry_key = var2.no_entry_key;
         this.no_entry_value = var2.no_entry_value;
         if (this.no_entry_key != 0) {
            Arrays.fill(this._set, this.no_entry_key);
         }

         if (this.no_entry_value != 0L) {
            Arrays.fill(this._values, this.no_entry_value);
         }

         this.setUp((int)Math.ceil((double)(10.0F / this._loadFactor)));
      }

      this.putAll(var1);
   }

   protected int setUp(int var1) {
      int var2 = super.setUp(var1);
      this._values = new long[var2];
      return var2;
   }

   protected void rehash(int var1) {
      int var2 = this._set.length;
      short[] var3 = this._set;
      long[] var4 = this._values;
      byte[] var5 = this._states;
      this._set = new short[var1];
      this._values = new long[var1];
      this._states = new byte[var1];
      int var6 = var2;

      while(var6-- > 0) {
         if (var5[var6] == 1) {
            short var7 = var3[var6];
            int var8 = this.insertKey(var7);
            this._values[var8] = var4[var6];
         }
      }

   }

   public long put(short var1, long var2) {
      int var4 = this.insertKey(var1);
      return this.doPut(var1, var2, var4);
   }

   public long putIfAbsent(short var1, long var2) {
      int var4 = this.insertKey(var1);
      return var4 < 0 ? this._values[-var4 - 1] : this.doPut(var1, var2, var4);
   }

   private long doPut(short var1, long var2, int var4) {
      long var5 = this.no_entry_value;
      boolean var7 = true;
      if (var4 < 0) {
         var4 = -var4 - 1;
         var5 = this._values[var4];
         var7 = false;
      }

      this._values[var4] = var2;
      if (var7) {
         this.postInsertHook(this.consumeFreeSlot);
      }

      return var5;
   }

   public void putAll(Map var1) {
      this.ensureCapacity(var1.size());
      Iterator var2 = var1.entrySet().iterator();

      while(var2.hasNext()) {
         Entry var3 = (Entry)var2.next();
         this.put((Short)var3.getKey(), (Long)var3.getValue());
      }

   }

   public void putAll(TShortLongMap var1) {
      this.ensureCapacity(var1.size());
      TShortLongIterator var2 = var1.iterator();

      while(var2.hasNext()) {
         var2.advance();
         this.put(var2.key(), var2.value());
      }

   }

   public long get(short var1) {
      int var2 = this.index(var1);
      return var2 < 0 ? this.no_entry_value : this._values[var2];
   }

   public void clear() {
      super.clear();
      Arrays.fill(this._set, 0, this._set.length, this.no_entry_key);
      Arrays.fill(this._values, 0, this._values.length, this.no_entry_value);
      Arrays.fill(this._states, 0, this._states.length, (byte)0);
   }

   public boolean isEmpty() {
      return 0 == this._size;
   }

   public long remove(short var1) {
      long var2 = this.no_entry_value;
      int var4 = this.index(var1);
      if (var4 >= 0) {
         var2 = this._values[var4];
         this.removeAt(var4);
      }

      return var2;
   }

   protected void removeAt(int var1) {
      this._values[var1] = this.no_entry_value;
      super.removeAt(var1);
   }

   public TShortSet keySet() {
      return new TShortLongHashMap.TKeyView(this);
   }

   public short[] keys() {
      short[] var1 = new short[this.size()];
      short[] var2 = this._set;
      byte[] var3 = this._states;
      int var4 = var2.length;
      int var5 = 0;

      while(var4-- > 0) {
         if (var3[var4] == 1) {
            var1[var5++] = var2[var4];
         }
      }

      return var1;
   }

   public short[] keys(short[] var1) {
      int var2 = this.size();
      if (var1.length < var2) {
         var1 = new short[var2];
      }

      short[] var3 = this._set;
      byte[] var4 = this._states;
      int var5 = var3.length;
      int var6 = 0;

      while(var5-- > 0) {
         if (var4[var5] == 1) {
            var1[var6++] = var3[var5];
         }
      }

      return var1;
   }

   public TLongCollection valueCollection() {
      return new TShortLongHashMap.TValueView(this);
   }

   public long[] values() {
      long[] var1 = new long[this.size()];
      long[] var2 = this._values;
      byte[] var3 = this._states;
      int var4 = var2.length;
      int var5 = 0;

      while(var4-- > 0) {
         if (var3[var4] == 1) {
            var1[var5++] = var2[var4];
         }
      }

      return var1;
   }

   public long[] values(long[] var1) {
      int var2 = this.size();
      if (var1.length < var2) {
         var1 = new long[var2];
      }

      long[] var3 = this._values;
      byte[] var4 = this._states;
      int var5 = var3.length;
      int var6 = 0;

      while(var5-- > 0) {
         if (var4[var5] == 1) {
            var1[var6++] = var3[var5];
         }
      }

      return var1;
   }

   public boolean containsValue(long var1) {
      byte[] var3 = this._states;
      long[] var4 = this._values;
      int var5 = var4.length;

      do {
         if (var5-- <= 0) {
            return false;
         }
      } while(var3[var5] != 1 || var1 != var4[var5]);

      return true;
   }

   public boolean containsKey(short var1) {
      return this.contains(var1);
   }

   public TShortLongIterator iterator() {
      return new TShortLongHashMap.TShortLongHashIterator(this, this);
   }

   public boolean forEachKey(TShortProcedure var1) {
      return this.forEach(var1);
   }

   public boolean forEachValue(TLongProcedure var1) {
      byte[] var2 = this._states;
      long[] var3 = this._values;
      int var4 = var3.length;

      do {
         if (var4-- <= 0) {
            return true;
         }
      } while(var2[var4] != 1 || var1.execute(var3[var4]));

      return false;
   }

   public boolean forEachEntry(TShortLongProcedure var1) {
      byte[] var2 = this._states;
      short[] var3 = this._set;
      long[] var4 = this._values;
      int var5 = var3.length;

      do {
         if (var5-- <= 0) {
            return true;
         }
      } while(var2[var5] != 1 || var1.execute(var3[var5], var4[var5]));

      return false;
   }

   public void transformValues(TLongFunction var1) {
      byte[] var2 = this._states;
      long[] var3 = this._values;
      int var4 = var3.length;

      while(var4-- > 0) {
         if (var2[var4] == 1) {
            var3[var4] = var1.execute(var3[var4]);
         }
      }

   }

   public boolean retainEntries(TShortLongProcedure var1) {
      boolean var2 = false;
      byte[] var3 = this._states;
      short[] var4 = this._set;
      long[] var5 = this._values;
      this.tempDisableAutoCompaction();
      int var6 = var4.length;

      while(var6-- > 0) {
         if (var3[var6] == 1 && !var1.execute(var4[var6], var5[var6])) {
            this.removeAt(var6);
            var2 = true;
         }
      }

      this.reenableAutoCompaction(true);
      return var2;
   }

   public boolean increment(short var1) {
      return this.adjustValue(var1, 1L);
   }

   public boolean adjustValue(short var1, long var2) {
      int var4 = this.index(var1);
      if (var4 < 0) {
         return false;
      } else {
         long[] var10000 = this._values;
         var10000[var4] += var2;
         return true;
      }
   }

   public long adjustOrPutValue(short var1, long var2, long var4) {
      int var6 = this.insertKey(var1);
      boolean var7;
      long var8;
      if (var6 < 0) {
         var6 = -var6 - 1;
         long[] var10000 = this._values;
         var8 = var10000[var6] += var2;
         var7 = false;
      } else {
         var8 = this._values[var6] = var4;
         var7 = true;
      }

      byte var11 = this._states[var6];
      if (var7) {
         this.postInsertHook(this.consumeFreeSlot);
      }

      return var8;
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof TShortLongMap)) {
         return false;
      } else {
         TShortLongMap var2 = (TShortLongMap)var1;
         if (var2.size() != this.size()) {
            return false;
         } else {
            long[] var3 = this._values;
            byte[] var4 = this._states;
            long var5 = this.getNoEntryValue();
            long var7 = var2.getNoEntryValue();
            int var9 = var3.length;

            while(var9-- > 0) {
               if (var4[var9] == 1) {
                  short var10 = this._set[var9];
                  long var11 = var2.get(var10);
                  long var13 = var3[var9];
                  if (var13 != var11 && var13 != var5 && var11 != var7) {
                     return false;
                  }
               }
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int var1 = 0;
      byte[] var2 = this._states;
      int var3 = this._values.length;

      while(var3-- > 0) {
         if (var2[var3] == 1) {
            var1 += HashFunctions.hash(this._set[var3]) ^ HashFunctions.hash(this._values[var3]);
         }
      }

      return var1;
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder("{");
      this.forEachEntry(new TShortLongProcedure(this, var1) {
         private boolean first;
         final StringBuilder val$buf;
         final TShortLongHashMap this$0;

         {
            this.this$0 = var1;
            this.val$buf = var2;
            this.first = true;
         }

         public boolean execute(short var1, long var2) {
            if (this.first) {
               this.first = false;
            } else {
               this.val$buf.append(", ");
            }

            this.val$buf.append(var1);
            this.val$buf.append("=");
            this.val$buf.append(var2);
            return true;
         }
      });
      var1.append("}");
      return var1.toString();
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeByte(0);
      super.writeExternal(var1);
      var1.writeInt(this._size);
      int var2 = this._states.length;

      while(var2-- > 0) {
         if (this._states[var2] == 1) {
            var1.writeShort(this._set[var2]);
            var1.writeLong(this._values[var2]);
         }
      }

   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      var1.readByte();
      super.readExternal(var1);
      int var2 = var1.readInt();
      this.setUp(var2);

      while(var2-- > 0) {
         short var3 = var1.readShort();
         long var4 = var1.readLong();
         this.put(var3, var4);
      }

   }

   static short access$000(TShortLongHashMap var0) {
      return var0.no_entry_key;
   }

   static int access$100(TShortLongHashMap var0) {
      return var0._size;
   }

   static int access$200(TShortLongHashMap var0) {
      return var0._size;
   }

   static long access$300(TShortLongHashMap var0) {
      return var0.no_entry_value;
   }

   static long access$400(TShortLongHashMap var0) {
      return var0.no_entry_value;
   }

   static int access$500(TShortLongHashMap var0) {
      return var0._size;
   }

   static int access$600(TShortLongHashMap var0) {
      return var0._size;
   }

   class TShortLongHashIterator extends THashPrimitiveIterator implements TShortLongIterator {
      final TShortLongHashMap this$0;

      TShortLongHashIterator(TShortLongHashMap var1, TShortLongHashMap var2) {
         super(var2);
         this.this$0 = var1;
      }

      public void advance() {
         this.moveToNextIndex();
      }

      public short key() {
         return this.this$0._set[this._index];
      }

      public long value() {
         return this.this$0._values[this._index];
      }

      public long setValue(long var1) {
         long var3 = this.value();
         this.this$0._values[this._index] = var1;
         return var3;
      }

      public void remove() {
         if (this._expectedSize != this._hash.size()) {
            throw new ConcurrentModificationException();
         } else {
            this._hash.tempDisableAutoCompaction();
            this.this$0.removeAt(this._index);
            this._hash.reenableAutoCompaction(false);
            --this._expectedSize;
         }
      }
   }

   class TShortLongValueHashIterator extends THashPrimitiveIterator implements TLongIterator {
      final TShortLongHashMap this$0;

      TShortLongValueHashIterator(TShortLongHashMap var1, TPrimitiveHash var2) {
         super(var2);
         this.this$0 = var1;
      }

      public long next() {
         this.moveToNextIndex();
         return this.this$0._values[this._index];
      }

      public void remove() {
         if (this._expectedSize != this._hash.size()) {
            throw new ConcurrentModificationException();
         } else {
            this._hash.tempDisableAutoCompaction();
            this.this$0.removeAt(this._index);
            this._hash.reenableAutoCompaction(false);
            --this._expectedSize;
         }
      }
   }

   class TShortLongKeyHashIterator extends THashPrimitiveIterator implements TShortIterator {
      final TShortLongHashMap this$0;

      TShortLongKeyHashIterator(TShortLongHashMap var1, TPrimitiveHash var2) {
         super(var2);
         this.this$0 = var1;
      }

      public short next() {
         this.moveToNextIndex();
         return this.this$0._set[this._index];
      }

      public void remove() {
         if (this._expectedSize != this._hash.size()) {
            throw new ConcurrentModificationException();
         } else {
            this._hash.tempDisableAutoCompaction();
            this.this$0.removeAt(this._index);
            this._hash.reenableAutoCompaction(false);
            --this._expectedSize;
         }
      }
   }

   protected class TValueView implements TLongCollection {
      final TShortLongHashMap this$0;

      protected TValueView(TShortLongHashMap var1) {
         this.this$0 = var1;
      }

      public TLongIterator iterator() {
         return this.this$0.new TShortLongValueHashIterator(this.this$0, this.this$0);
      }

      public long getNoEntryValue() {
         return TShortLongHashMap.access$400(this.this$0);
      }

      public int size() {
         return TShortLongHashMap.access$500(this.this$0);
      }

      public boolean isEmpty() {
         return 0 == TShortLongHashMap.access$600(this.this$0);
      }

      public boolean contains(long var1) {
         return this.this$0.containsValue(var1);
      }

      public long[] toArray() {
         return this.this$0.values();
      }

      public long[] toArray(long[] var1) {
         return this.this$0.values(var1);
      }

      public boolean add(long var1) {
         throw new UnsupportedOperationException();
      }

      public boolean containsAll(Collection var1) {
         Iterator var2 = var1.iterator();

         long var4;
         do {
            if (!var2.hasNext()) {
               return true;
            }

            Object var3 = var2.next();
            if (!(var3 instanceof Long)) {
               return false;
            }

            var4 = (Long)var3;
         } while(this.this$0.containsValue(var4));

         return false;
      }

      public boolean containsAll(TLongCollection var1) {
         TLongIterator var2 = var1.iterator();

         do {
            if (!var2.hasNext()) {
               return true;
            }
         } while(this.this$0.containsValue(var2.next()));

         return false;
      }

      public boolean containsAll(long[] var1) {
         long[] var2 = var1;
         int var3 = var1.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            long var5 = var2[var4];
            if (!this.this$0.containsValue(var5)) {
               return false;
            }
         }

         return true;
      }

      public boolean addAll(Collection var1) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(TLongCollection var1) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(long[] var1) {
         throw new UnsupportedOperationException();
      }

      public boolean retainAll(Collection var1) {
         boolean var2 = false;
         TLongIterator var3 = this.iterator();

         while(var3.hasNext()) {
            if (!var1.contains(var3.next())) {
               var3.remove();
               var2 = true;
            }
         }

         return var2;
      }

      public boolean retainAll(TLongCollection var1) {
         if (this == var1) {
            return false;
         } else {
            boolean var2 = false;
            TLongIterator var3 = this.iterator();

            while(var3.hasNext()) {
               if (!var1.contains(var3.next())) {
                  var3.remove();
                  var2 = true;
               }
            }

            return var2;
         }
      }

      public boolean retainAll(long[] var1) {
         boolean var2 = false;
         Arrays.sort(var1);
         long[] var3 = this.this$0._values;
         byte[] var4 = this.this$0._states;
         int var5 = var3.length;

         while(var5-- > 0) {
            if (var4[var5] == 1 && Arrays.binarySearch(var1, var3[var5]) < 0) {
               this.this$0.removeAt(var5);
               var2 = true;
            }
         }

         return var2;
      }

      public boolean removeAll(Collection var1) {
         boolean var2 = false;
         Iterator var3 = var1.iterator();

         while(var3.hasNext()) {
            Object var4 = var3.next();
            if (var4 instanceof Long) {
               long var5 = (Long)var4;
               if (var5 > 0) {
                  var2 = true;
               }
            }
         }

         return var2;
      }

      public boolean removeAll(TLongCollection var1) {
         if (this == var1) {
            this.clear();
            return true;
         } else {
            boolean var2 = false;
            TLongIterator var3 = var1.iterator();

            while(var3.hasNext()) {
               long var4 = var3.next();
               if (var4 > 0) {
                  var2 = true;
               }
            }

            return var2;
         }
      }

      public boolean removeAll(long[] var1) {
         boolean var2 = false;
         int var3 = var1.length;

         while(var3-- > 0) {
            if (var1[var3] > 0) {
               var2 = true;
            }
         }

         return var2;
      }

      public void clear() {
         this.this$0.clear();
      }

      public boolean forEach(TLongProcedure var1) {
         return this.this$0.forEachValue(var1);
      }

      public String toString() {
         StringBuilder var1 = new StringBuilder("{");
         this.this$0.forEachValue(new TLongProcedure(this, var1) {
            private boolean first;
            final StringBuilder val$buf;
            final TShortLongHashMap.TValueView this$1;

            {
               this.this$1 = var1;
               this.val$buf = var2;
               this.first = true;
            }

            public boolean execute(long var1) {
               if (this.first) {
                  this.first = false;
               } else {
                  this.val$buf.append(", ");
               }

               this.val$buf.append(var1);
               return true;
            }
         });
         var1.append("}");
         return var1.toString();
      }
   }

   protected class TKeyView implements TShortSet {
      final TShortLongHashMap this$0;

      protected TKeyView(TShortLongHashMap var1) {
         this.this$0 = var1;
      }

      public TShortIterator iterator() {
         return this.this$0.new TShortLongKeyHashIterator(this.this$0, this.this$0);
      }

      public short getNoEntryValue() {
         return TShortLongHashMap.access$000(this.this$0);
      }

      public int size() {
         return TShortLongHashMap.access$100(this.this$0);
      }

      public boolean isEmpty() {
         return 0 == TShortLongHashMap.access$200(this.this$0);
      }

      public boolean contains(short var1) {
         return this.this$0.contains(var1);
      }

      public short[] toArray() {
         return this.this$0.keys();
      }

      public short[] toArray(short[] var1) {
         return this.this$0.keys(var1);
      }

      public boolean add(short var1) {
         throw new UnsupportedOperationException();
      }

      public boolean containsAll(Collection var1) {
         Iterator var2 = var1.iterator();

         short var4;
         do {
            if (!var2.hasNext()) {
               return true;
            }

            Object var3 = var2.next();
            if (!(var3 instanceof Short)) {
               return false;
            }

            var4 = (Short)var3;
         } while(this.this$0.containsKey(var4));

         return false;
      }

      public boolean containsAll(TShortCollection var1) {
         TShortIterator var2 = var1.iterator();

         do {
            if (!var2.hasNext()) {
               return true;
            }
         } while(this.this$0.containsKey(var2.next()));

         return false;
      }

      public boolean containsAll(short[] var1) {
         short[] var2 = var1;
         int var3 = var1.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            short var5 = var2[var4];
            if (!this.this$0.contains(var5)) {
               return false;
            }
         }

         return true;
      }

      public boolean addAll(Collection var1) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(TShortCollection var1) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(short[] var1) {
         throw new UnsupportedOperationException();
      }

      public boolean retainAll(Collection var1) {
         boolean var2 = false;
         TShortIterator var3 = this.iterator();

         while(var3.hasNext()) {
            if (!var1.contains(var3.next())) {
               var3.remove();
               var2 = true;
            }
         }

         return var2;
      }

      public boolean retainAll(TShortCollection var1) {
         if (this == var1) {
            return false;
         } else {
            boolean var2 = false;
            TShortIterator var3 = this.iterator();

            while(var3.hasNext()) {
               if (!var1.contains(var3.next())) {
                  var3.remove();
                  var2 = true;
               }
            }

            return var2;
         }
      }

      public boolean retainAll(short[] var1) {
         boolean var2 = false;
         Arrays.sort(var1);
         short[] var3 = this.this$0._set;
         byte[] var4 = this.this$0._states;
         int var5 = var3.length;

         while(var5-- > 0) {
            if (var4[var5] == 1 && Arrays.binarySearch(var1, var3[var5]) < 0) {
               this.this$0.removeAt(var5);
               var2 = true;
            }
         }

         return var2;
      }

      public boolean removeAll(Collection var1) {
         boolean var2 = false;
         Iterator var3 = var1.iterator();

         while(var3.hasNext()) {
            Object var4 = var3.next();
            if (var4 instanceof Short) {
               short var5 = (Short)var4;
               if (var5 != 0) {
                  var2 = true;
               }
            }
         }

         return var2;
      }

      public boolean removeAll(TShortCollection var1) {
         if (this == var1) {
            this.clear();
            return true;
         } else {
            boolean var2 = false;
            TShortIterator var3 = var1.iterator();

            while(var3.hasNext()) {
               short var4 = var3.next();
               if (var4 != 0) {
                  var2 = true;
               }
            }

            return var2;
         }
      }

      public boolean removeAll(short[] var1) {
         boolean var2 = false;
         int var3 = var1.length;

         while(var3-- > 0) {
            if (var1[var3] != 0) {
               var2 = true;
            }
         }

         return var2;
      }

      public void clear() {
         this.this$0.clear();
      }

      public boolean forEach(TShortProcedure var1) {
         return this.this$0.forEachKey(var1);
      }

      public boolean equals(Object var1) {
         if (!(var1 instanceof TShortSet)) {
            return false;
         } else {
            TShortSet var2 = (TShortSet)var1;
            if (var2.size() != this.size()) {
               return false;
            } else {
               int var3 = this.this$0._states.length;

               do {
                  if (var3-- <= 0) {
                     return true;
                  }
               } while(this.this$0._states[var3] != 1 || var2.contains(this.this$0._set[var3]));

               return false;
            }
         }
      }

      public int hashCode() {
         int var1 = 0;
         int var2 = this.this$0._states.length;

         while(var2-- > 0) {
            if (this.this$0._states[var2] == 1) {
               var1 += HashFunctions.hash(this.this$0._set[var2]);
            }
         }

         return var1;
      }

      public String toString() {
         StringBuilder var1 = new StringBuilder("{");
         this.this$0.forEachKey(new TShortProcedure(this, var1) {
            private boolean first;
            final StringBuilder val$buf;
            final TShortLongHashMap.TKeyView this$1;

            {
               this.this$1 = var1;
               this.val$buf = var2;
               this.first = true;
            }

            public boolean execute(short var1) {
               if (this.first) {
                  this.first = false;
               } else {
                  this.val$buf.append(", ");
               }

               this.val$buf.append(var1);
               return true;
            }
         });
         var1.append("}");
         return var1.toString();
      }
   }
}
