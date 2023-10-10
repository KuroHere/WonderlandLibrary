package gnu.trove.list.array;

import gnu.trove.TCharCollection;
import gnu.trove.function.TCharFunction;
import gnu.trove.impl.HashFunctions;
import gnu.trove.iterator.TCharIterator;
import gnu.trove.list.TCharList;
import gnu.trove.procedure.TCharProcedure;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class TCharArrayList implements TCharList, Externalizable {
   static final long serialVersionUID = 1L;
   protected char[] _data;
   protected int _pos;
   protected static final int DEFAULT_CAPACITY = 10;
   protected char no_entry_value;

   public TCharArrayList() {
      this(10, '\u0000');
   }

   public TCharArrayList(int var1) {
      this(var1, '\u0000');
   }

   public TCharArrayList(int var1, char var2) {
      this._data = new char[var1];
      this._pos = 0;
      this.no_entry_value = var2;
   }

   public TCharArrayList(TCharCollection var1) {
      this(var1.size());
      this.addAll(var1);
   }

   public TCharArrayList(char[] var1) {
      this(var1.length);
      this.add(var1);
   }

   protected TCharArrayList(char[] var1, char var2, boolean var3) {
      if (!var3) {
         throw new IllegalStateException("Wrong call");
      } else if (var1 == null) {
         throw new IllegalArgumentException("values can not be null");
      } else {
         this._data = var1;
         this._pos = var1.length;
         this.no_entry_value = var2;
      }
   }

   public static TCharArrayList wrap(char[] var0) {
      return wrap(var0, '\u0000');
   }

   public static TCharArrayList wrap(char[] var0, char var1) {
      return new TCharArrayList(var0, var1, true) {
         public void ensureCapacity(int var1) {
            if (var1 > this._data.length) {
               throw new IllegalStateException("Can not grow ArrayList wrapped external array");
            }
         }
      };
   }

   public char getNoEntryValue() {
      return this.no_entry_value;
   }

   public void ensureCapacity(int var1) {
      if (var1 > this._data.length) {
         int var2 = Math.max(this._data.length << 1, var1);
         char[] var3 = new char[var2];
         System.arraycopy(this._data, 0, var3, 0, this._data.length);
         this._data = var3;
      }

   }

   public int size() {
      return this._pos;
   }

   public boolean isEmpty() {
      return this._pos == 0;
   }

   public void trimToSize() {
      if (this._data.length > this.size()) {
         char[] var1 = new char[this.size()];
         this.toArray(var1, 0, var1.length);
         this._data = var1;
      }

   }

   public boolean add(char var1) {
      this.ensureCapacity(this._pos + 1);
      this._data[this._pos++] = var1;
      return true;
   }

   public void add(char[] var1) {
      this.add(var1, 0, var1.length);
   }

   public void add(char[] var1, int var2, int var3) {
      this.ensureCapacity(this._pos + var3);
      System.arraycopy(var1, var2, this._data, this._pos, var3);
      this._pos += var3;
   }

   public void insert(int var1, char var2) {
      if (var1 == this._pos) {
         this.add(var2);
      } else {
         this.ensureCapacity(this._pos + 1);
         System.arraycopy(this._data, var1, this._data, var1 + 1, this._pos - var1);
         this._data[var1] = var2;
         ++this._pos;
      }
   }

   public void insert(int var1, char[] var2) {
      this.insert(var1, var2, 0, var2.length);
   }

   public void insert(int var1, char[] var2, int var3, int var4) {
      if (var1 == this._pos) {
         this.add(var2, var3, var4);
      } else {
         this.ensureCapacity(this._pos + var4);
         System.arraycopy(this._data, var1, this._data, var1 + var4, this._pos - var1);
         System.arraycopy(var2, var3, this._data, var1, var4);
         this._pos += var4;
      }
   }

   public char get(int var1) {
      if (var1 >= this._pos) {
         throw new ArrayIndexOutOfBoundsException(var1);
      } else {
         return this._data[var1];
      }
   }

   public char getQuick(int var1) {
      return this._data[var1];
   }

   public char set(int var1, char var2) {
      if (var1 >= this._pos) {
         throw new ArrayIndexOutOfBoundsException(var1);
      } else {
         char var3 = this._data[var1];
         this._data[var1] = var2;
         return var3;
      }
   }

   public char replace(int var1, char var2) {
      if (var1 >= this._pos) {
         throw new ArrayIndexOutOfBoundsException(var1);
      } else {
         char var3 = this._data[var1];
         this._data[var1] = var2;
         return var3;
      }
   }

   public void set(int var1, char[] var2) {
      this.set(var1, var2, 0, var2.length);
   }

   public void set(int var1, char[] var2, int var3, int var4) {
      if (var1 >= 0 && var1 + var4 <= this._pos) {
         System.arraycopy(var2, var3, this._data, var1, var4);
      } else {
         throw new ArrayIndexOutOfBoundsException(var1);
      }
   }

   public void setQuick(int var1, char var2) {
      this._data[var1] = var2;
   }

   public void clear() {
      this.clear(10);
   }

   public void clear(int var1) {
      this._data = new char[var1];
      this._pos = 0;
   }

   public void reset() {
      this._pos = 0;
      Arrays.fill(this._data, this.no_entry_value);
   }

   public void resetQuick() {
      this._pos = 0;
   }

   public char removeAt(int var1) {
      char var2 = this.get(var1);
      this.remove(var1, 1);
      return var2;
   }

   public void remove(int var1, int var2) {
      if (var2 != 0) {
         if (var1 >= 0 && var1 < this._pos) {
            if (var1 == 0) {
               System.arraycopy(this._data, var2, this._data, 0, this._pos - var2);
            } else if (this._pos - var2 != var1) {
               System.arraycopy(this._data, var1 + var2, this._data, var1, this._pos - (var1 + var2));
            }

            this._pos -= var2;
         } else {
            throw new ArrayIndexOutOfBoundsException(var1);
         }
      }
   }

   public TCharIterator iterator() {
      return new TCharArrayList.TCharArrayIterator(this, 0);
   }

   public boolean containsAll(Collection var1) {
      Iterator var2 = var1.iterator();

      char var4;
      do {
         if (!var2.hasNext()) {
            return true;
         }

         Object var3 = var2.next();
         if (!(var3 instanceof Character)) {
            return false;
         }

         var4 = (Character)var3;
      } while(var4 < 0);

      return false;
   }

   public boolean containsAll(TCharCollection var1) {
      if (this == var1) {
         return true;
      } else {
         TCharIterator var2 = var1.iterator();

         char var3;
         do {
            if (!var2.hasNext()) {
               return true;
            }

            var3 = var2.next();
         } while(var3 < 0);

         return false;
      }
   }

   public boolean containsAll(char[] var1) {
      int var2 = var1.length;

      do {
         if (var2-- <= 0) {
            return true;
         }
      } while(var1[var2] < 0);

      return false;
   }

   public boolean addAll(Collection var1) {
      boolean var2 = false;
      Iterator var3 = var1.iterator();

      while(var3.hasNext()) {
         Character var4 = (Character)var3.next();
         char var5 = var4;
         if (this.add(var5)) {
            var2 = true;
         }
      }

      return var2;
   }

   public boolean addAll(TCharCollection var1) {
      boolean var2 = false;
      TCharIterator var3 = var1.iterator();

      while(var3.hasNext()) {
         char var4 = var3.next();
         if (this.add(var4)) {
            var2 = true;
         }
      }

      return var2;
   }

   public boolean addAll(char[] var1) {
      boolean var2 = false;
      char[] var3 = var1;
      int var4 = var1.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         char var6 = var3[var5];
         if (this.add(var6)) {
            var2 = true;
         }
      }

      return var2;
   }

   public boolean retainAll(Collection var1) {
      boolean var2 = false;
      TCharIterator var3 = this.iterator();

      while(var3.hasNext()) {
         if (!var1.contains(var3.next())) {
            var3.remove();
            var2 = true;
         }
      }

      return var2;
   }

   public boolean retainAll(TCharCollection var1) {
      if (this == var1) {
         return false;
      } else {
         boolean var2 = false;
         TCharIterator var3 = this.iterator();

         while(var3.hasNext()) {
            if (!var1.contains(var3.next())) {
               var3.remove();
               var2 = true;
            }
         }

         return var2;
      }
   }

   public boolean retainAll(char[] var1) {
      boolean var2 = false;
      Arrays.sort(var1);
      char[] var3 = this._data;
      int var4 = this._pos;

      while(var4-- > 0) {
         if (Arrays.binarySearch(var1, var3[var4]) < 0) {
            this.remove(var4, 1);
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
         if (var4 instanceof Character) {
            char var5 = (Character)var4;
            if (this < var5) {
               var2 = true;
            }
         }
      }

      return var2;
   }

   public boolean removeAll(TCharCollection var1) {
      if (var1 == this) {
         this.clear();
         return true;
      } else {
         boolean var2 = false;
         TCharIterator var3 = var1.iterator();

         while(var3.hasNext()) {
            char var4 = var3.next();
            if (this < var4) {
               var2 = true;
            }
         }

         return var2;
      }
   }

   public boolean removeAll(char[] var1) {
      boolean var2 = false;
      int var3 = var1.length;

      while(var3-- > 0) {
         if (this < var1[var3]) {
            var2 = true;
         }
      }

      return var2;
   }

   public void transformValues(TCharFunction var1) {
      for(int var2 = this._pos; var2-- > 0; this._data[var2] = var1.execute(this._data[var2])) {
      }

   }

   public void reverse() {
      this.reverse(0, this._pos);
   }

   public void reverse(int var1, int var2) {
      if (var1 != var2) {
         if (var1 > var2) {
            throw new IllegalArgumentException("from cannot be greater than to");
         } else {
            int var3 = var1;

            for(int var4 = var2 - 1; var3 < var4; --var4) {
               this.swap(var3, var4);
               ++var3;
            }

         }
      }
   }

   public void shuffle(Random var1) {
      int var2 = this._pos;

      while(var2-- > 1) {
         this.swap(var2, var1.nextInt(var2));
      }

   }

   private void swap(int var1, int var2) {
      char var3 = this._data[var1];
      this._data[var1] = this._data[var2];
      this._data[var2] = var3;
   }

   public TCharList subList(int var1, int var2) {
      if (var2 < var1) {
         throw new IllegalArgumentException("end index " + var2 + " greater than begin index " + var1);
      } else if (var1 < 0) {
         throw new IndexOutOfBoundsException("begin index can not be < 0");
      } else if (var2 > this._data.length) {
         throw new IndexOutOfBoundsException("end index < " + this._data.length);
      } else {
         TCharArrayList var3 = new TCharArrayList(var2 - var1);

         for(int var4 = var1; var4 < var2; ++var4) {
            var3.add(this._data[var4]);
         }

         return var3;
      }
   }

   public char[] toArray() {
      return this.toArray(0, this._pos);
   }

   public char[] toArray(int var1, int var2) {
      char[] var3 = new char[var2];
      this.toArray(var3, var1, var2);
      return var3;
   }

   public char[] toArray(char[] var1) {
      int var2 = var1.length;
      if (var1.length > this._pos) {
         var2 = this._pos;
         var1[var2] = this.no_entry_value;
      }

      this.toArray(var1, 0, var2);
      return var1;
   }

   public char[] toArray(char[] var1, int var2, int var3) {
      if (var3 == 0) {
         return var1;
      } else if (var2 >= 0 && var2 < this._pos) {
         System.arraycopy(this._data, var2, var1, 0, var3);
         return var1;
      } else {
         throw new ArrayIndexOutOfBoundsException(var2);
      }
   }

   public char[] toArray(char[] var1, int var2, int var3, int var4) {
      if (var4 == 0) {
         return var1;
      } else if (var2 >= 0 && var2 < this._pos) {
         System.arraycopy(this._data, var2, var1, var3, var4);
         return var1;
      } else {
         throw new ArrayIndexOutOfBoundsException(var2);
      }
   }

   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (var1 instanceof TCharArrayList) {
         TCharArrayList var2 = (TCharArrayList)var1;
         if (var2.size() != this.size()) {
            return false;
         } else {
            int var3 = this._pos;

            do {
               if (var3-- <= 0) {
                  return true;
               }
            } while(this._data[var3] == var2._data[var3]);

            return false;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      int var1 = 0;

      for(int var2 = this._pos; var2-- > 0; var1 += HashFunctions.hash(this._data[var2])) {
      }

      return var1;
   }

   public boolean forEach(TCharProcedure var1) {
      for(int var2 = 0; var2 < this._pos; ++var2) {
         if (!var1.execute(this._data[var2])) {
            return false;
         }
      }

      return true;
   }

   public boolean forEachDescending(TCharProcedure var1) {
      int var2 = this._pos;

      do {
         if (var2-- <= 0) {
            return true;
         }
      } while(var1.execute(this._data[var2]));

      return false;
   }

   public void sort() {
      Arrays.sort(this._data, 0, this._pos);
   }

   public void sort(int var1, int var2) {
      Arrays.sort(this._data, var1, var2);
   }

   public void fill(char var1) {
      Arrays.fill(this._data, 0, this._pos, var1);
   }

   public void fill(int var1, int var2, char var3) {
      if (var2 > this._pos) {
         this.ensureCapacity(var2);
         this._pos = var2;
      }

      Arrays.fill(this._data, var1, var2, var3);
   }

   public int binarySearch(char var1) {
      return this.binarySearch(var1, 0, this._pos);
   }

   public int binarySearch(char var1, int var2, int var3) {
      if (var2 < 0) {
         throw new ArrayIndexOutOfBoundsException(var2);
      } else if (var3 > this._pos) {
         throw new ArrayIndexOutOfBoundsException(var3);
      } else {
         int var4 = var2;
         int var5 = var3 - 1;

         while(var4 <= var5) {
            int var6 = var4 + var5 >>> 1;
            char var7 = this._data[var6];
            if (var7 < var1) {
               var4 = var6 + 1;
            } else {
               if (var7 <= var1) {
                  return var6;
               }

               var5 = var6 - 1;
            }
         }

         return -(var4 + 1);
      }
   }

   public int indexOf(char var1) {
      return this.indexOf(0, var1);
   }

   public int indexOf(int var1, char var2) {
      for(int var3 = var1; var3 < this._pos; ++var3) {
         if (this._data[var3] == var2) {
            return var3;
         }
      }

      return -1;
   }

   public int lastIndexOf(char var1) {
      return this.lastIndexOf(this._pos, var1);
   }

   public int lastIndexOf(int var1, char var2) {
      int var3 = var1;

      do {
         if (var3-- <= 0) {
            return -1;
         }
      } while(this._data[var3] != var2);

      return var3;
   }

   public TCharList grep(TCharProcedure var1) {
      TCharArrayList var2 = new TCharArrayList();

      for(int var3 = 0; var3 < this._pos; ++var3) {
         if (var1.execute(this._data[var3])) {
            var2.add(this._data[var3]);
         }
      }

      return var2;
   }

   public TCharList inverseGrep(TCharProcedure var1) {
      TCharArrayList var2 = new TCharArrayList();

      for(int var3 = 0; var3 < this._pos; ++var3) {
         if (!var1.execute(this._data[var3])) {
            var2.add(this._data[var3]);
         }
      }

      return var2;
   }

   public char max() {
      if (this.size() == 0) {
         throw new IllegalStateException("cannot find maximum of an empty list");
      } else {
         char var1 = 0;

         for(int var2 = 0; var2 < this._pos; ++var2) {
            if (this._data[var2] > var1) {
               var1 = this._data[var2];
            }
         }

         return var1;
      }
   }

   public char min() {
      if (this.size() == 0) {
         throw new IllegalStateException("cannot find minimum of an empty list");
      } else {
         char var1 = '\uffff';

         for(int var2 = 0; var2 < this._pos; ++var2) {
            if (this._data[var2] < var1) {
               var1 = this._data[var2];
            }
         }

         return var1;
      }
   }

   public char sum() {
      char var1 = 0;

      for(int var2 = 0; var2 < this._pos; ++var2) {
         var1 += this._data[var2];
      }

      return var1;
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder("{");
      int var2 = 0;

      for(int var3 = this._pos - 1; var2 < var3; ++var2) {
         var1.append(this._data[var2]);
         var1.append(", ");
      }

      if (this.size() > 0) {
         var1.append(this._data[this._pos - 1]);
      }

      var1.append("}");
      return var1.toString();
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeByte(0);
      var1.writeInt(this._pos);
      var1.writeChar(this.no_entry_value);
      int var2 = this._data.length;
      var1.writeInt(var2);

      for(int var3 = 0; var3 < var2; ++var3) {
         var1.writeChar(this._data[var3]);
      }

   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      var1.readByte();
      this._pos = var1.readInt();
      this.no_entry_value = var1.readChar();
      int var2 = var1.readInt();
      this._data = new char[var2];

      for(int var3 = 0; var3 < var2; ++var3) {
         this._data[var3] = var1.readChar();
      }

   }

   class TCharArrayIterator implements TCharIterator {
      private int cursor;
      int lastRet;
      final TCharArrayList this$0;

      TCharArrayIterator(TCharArrayList var1, int var2) {
         this.this$0 = var1;
         this.cursor = 0;
         this.lastRet = -1;
         this.cursor = var2;
      }

      public boolean hasNext() {
         return this.cursor < this.this$0.size();
      }

      public char next() {
         try {
            char var1 = this.this$0.get(this.cursor);
            this.lastRet = this.cursor++;
            return var1;
         } catch (IndexOutOfBoundsException var2) {
            throw new NoSuchElementException();
         }
      }

      public void remove() {
         if (this.lastRet == -1) {
            throw new IllegalStateException();
         } else {
            try {
               this.this$0.remove(this.lastRet, 1);
               if (this.lastRet < this.cursor) {
                  --this.cursor;
               }

               this.lastRet = -1;
            } catch (IndexOutOfBoundsException var2) {
               throw new ConcurrentModificationException();
            }
         }
      }
   }
}
