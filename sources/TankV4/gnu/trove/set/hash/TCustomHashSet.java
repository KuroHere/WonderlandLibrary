package gnu.trove.set.hash;

import gnu.trove.impl.HashFunctions;
import gnu.trove.impl.hash.TCustomObjectHash;
import gnu.trove.iterator.hash.TObjectHashIterator;
import gnu.trove.procedure.TObjectProcedure;
import gnu.trove.procedure.array.ToObjectArrayProceedure;
import gnu.trove.strategy.HashingStrategy;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class TCustomHashSet extends TCustomObjectHash implements Set, Iterable, Externalizable {
   static final long serialVersionUID = 1L;

   public TCustomHashSet() {
   }

   public TCustomHashSet(HashingStrategy var1) {
      super(var1);
   }

   public TCustomHashSet(HashingStrategy var1, int var2) {
      super(var1, var2);
   }

   public TCustomHashSet(HashingStrategy var1, int var2, float var3) {
      super(var1, var2, var3);
   }

   public TCustomHashSet(HashingStrategy var1, Collection var2) {
      this(var1, var2.size());
      this.addAll(var2);
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof Set)) {
         return false;
      } else {
         Set var2 = (Set)var1;
         return var2.size() != this.size() ? false : this.containsAll(var2);
      }
   }

   public int hashCode() {
      TCustomHashSet.HashProcedure var1 = new TCustomHashSet.HashProcedure(this);
      this.forEach(var1);
      return var1.getHashCode();
   }

   protected void rehash(int var1) {
      int var2 = this._set.length;
      int var3 = this.size();
      Object[] var4 = this._set;
      this._set = new Object[var1];
      Arrays.fill(this._set, FREE);
      int var5 = var2;

      while(var5-- > 0) {
         Object var6 = var4[var5];
         if (var6 != FREE && var6 != REMOVED) {
            int var7 = this.insertKey(var6);
            if (var7 < 0) {
               this.throwObjectContractViolation(this._set[-var7 - 1], var6, this.size(), var3, var4);
            }
         }
      }

   }

   public Object[] toArray() {
      Object[] var1 = new Object[this.size()];
      this.forEach(new ToObjectArrayProceedure(var1));
      return var1;
   }

   public Object[] toArray(Object[] var1) {
      int var2 = this.size();
      if (var1.length < var2) {
         var1 = (Object[])((Object[])Array.newInstance(var1.getClass().getComponentType(), var2));
      }

      this.forEach(new ToObjectArrayProceedure(var1));
      if (var1.length > var2) {
         var1[var2] = null;
      }

      return var1;
   }

   public void clear() {
      super.clear();
      Arrays.fill(this._set, 0, this._set.length, FREE);
   }

   public TObjectHashIterator iterator() {
      return new TObjectHashIterator(this);
   }

   public boolean containsAll(Collection var1) {
      Iterator var2 = var1.iterator();

      do {
         if (!var2.hasNext()) {
            return true;
         }
      } while(this.contains(var2.next()));

      return false;
   }

   public boolean addAll(Collection var1) {
      boolean var2 = false;
      int var3 = var1.size();
      this.ensureCapacity(var3);
      Iterator var4 = var1.iterator();

      while(var3-- > 0) {
         if (var4.next() < 0) {
            var2 = true;
         }
      }

      return var2;
   }

   public boolean removeAll(Collection var1) {
      boolean var2 = false;
      int var3 = var1.size();
      Iterator var4 = var1.iterator();

      while(var3-- > 0) {
         if (var4.next() >= 0) {
            var2 = true;
         }
      }

      return var2;
   }

   public boolean retainAll(Collection var1) {
      boolean var2 = false;
      int var3 = this.size();
      TObjectHashIterator var4 = this.iterator();

      while(var3-- > 0) {
         if (!var1.contains(var4.next())) {
            var4.remove();
            var2 = true;
         }
      }

      return var2;
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder("{");
      this.forEach(new TObjectProcedure(this, var1) {
         private boolean first;
         final StringBuilder val$buf;
         final TCustomHashSet this$0;

         {
            this.this$0 = var1;
            this.val$buf = var2;
            this.first = true;
         }

         public boolean execute(Object var1) {
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

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeByte(1);
      super.writeExternal(var1);
      var1.writeInt(this._size);
      int var2 = this._set.length;

      while(var2-- > 0) {
         if (this._set[var2] != REMOVED && this._set[var2] != FREE) {
            var1.writeObject(this._set[var2]);
         }
      }

   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      byte var2 = var1.readByte();
      if (var2 != 0) {
         super.readExternal(var1);
      }

      int var3 = var1.readInt();
      this.setUp(var3);

      while(var3-- > 0) {
         Object var4 = var1.readObject();
         this.add(var4);
      }

   }

   public Iterator iterator() {
      return this.iterator();
   }

   private final class HashProcedure implements TObjectProcedure {
      private int h;
      final TCustomHashSet this$0;

      private HashProcedure(TCustomHashSet var1) {
         this.this$0 = var1;
         this.h = 0;
      }

      public int getHashCode() {
         return this.h;
      }

      public final boolean execute(Object var1) {
         this.h += HashFunctions.hash(var1);
         return true;
      }

      HashProcedure(TCustomHashSet var1, Object var2) {
         this(var1);
      }
   }
}
