package gnu.trove.stack.array;

import gnu.trove.list.array.TFloatArrayList;
import gnu.trove.stack.TFloatStack;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class TFloatArrayStack implements TFloatStack, Externalizable {
   static final long serialVersionUID = 1L;
   protected TFloatArrayList _list;
   public static final int DEFAULT_CAPACITY = 10;

   public TFloatArrayStack() {
      this(10);
   }

   public TFloatArrayStack(int var1) {
      this._list = new TFloatArrayList(var1);
   }

   public TFloatArrayStack(int var1, float var2) {
      this._list = new TFloatArrayList(var1, var2);
   }

   public TFloatArrayStack(TFloatStack var1) {
      if (var1 instanceof TFloatArrayStack) {
         TFloatArrayStack var2 = (TFloatArrayStack)var1;
         this._list = new TFloatArrayList(var2._list);
      } else {
         throw new UnsupportedOperationException("Only support TFloatArrayStack");
      }
   }

   public float getNoEntryValue() {
      return this._list.getNoEntryValue();
   }

   public void push(float var1) {
      this._list.add(var1);
   }

   public float pop() {
      return this._list.removeAt(this._list.size() - 1);
   }

   public float peek() {
      return this._list.get(this._list.size() - 1);
   }

   public int size() {
      return this._list.size();
   }

   public void clear() {
      this._list.clear();
   }

   public float[] toArray() {
      float[] var1 = this._list.toArray();
      this.reverse(var1, 0, this.size());
      return var1;
   }

   public void toArray(float[] var1) {
      int var2 = this.size();
      int var3 = var2 - var1.length;
      if (var3 < 0) {
         var3 = 0;
      }

      int var4 = Math.min(var2, var1.length);
      this._list.toArray(var1, var3, var4);
      this.reverse(var1, 0, var4);
      if (var1.length > var2) {
         var1[var2] = this._list.getNoEntryValue();
      }

   }

   private void reverse(float[] var1, int var2, int var3) {
      if (var2 != var3) {
         if (var2 > var3) {
            throw new IllegalArgumentException("from cannot be greater than to");
         } else {
            int var4 = var2;

            for(int var5 = var3 - 1; var4 < var5; --var5) {
               this.swap(var1, var4, var5);
               ++var4;
            }

         }
      }
   }

   private void swap(float[] var1, int var2, int var3) {
      float var4 = var1[var2];
      var1[var2] = var1[var3];
      var1[var3] = var4;
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder("{");

      for(int var2 = this._list.size() - 1; var2 > 0; --var2) {
         var1.append(this._list.get(var2));
         var1.append(", ");
      }

      if (this.size() > 0) {
         var1.append(this._list.get(0));
      }

      var1.append("}");
      return var1.toString();
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         TFloatArrayStack var2 = (TFloatArrayStack)var1;
         return this._list.equals(var2._list);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this._list.hashCode();
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeByte(0);
      var1.writeObject(this._list);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      var1.readByte();
      this._list = (TFloatArrayList)var1.readObject();
   }
}
