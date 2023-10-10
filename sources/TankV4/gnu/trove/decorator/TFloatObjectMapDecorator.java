package gnu.trove.decorator;

import gnu.trove.iterator.TFloatObjectIterator;
import gnu.trove.map.TFloatObjectMap;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class TFloatObjectMapDecorator extends AbstractMap implements Map, Externalizable, Cloneable {
   static final long serialVersionUID = 1L;
   protected TFloatObjectMap _map;

   public TFloatObjectMapDecorator() {
   }

   public TFloatObjectMapDecorator(TFloatObjectMap var1) {
      this._map = var1;
   }

   public TFloatObjectMap getMap() {
      return this._map;
   }

   public Object put(Float var1, Object var2) {
      float var3;
      if (var1 == null) {
         var3 = this._map.getNoEntryKey();
      } else {
         var3 = this.unwrapKey(var1);
      }

      return this._map.put(var3, var2);
   }

   public Object get(Object var1) {
      float var2;
      if (var1 != null) {
         if (!(var1 instanceof Float)) {
            return null;
         }

         var2 = this.unwrapKey((Float)var1);
      } else {
         var2 = this._map.getNoEntryKey();
      }

      return this._map.get(var2);
   }

   public void clear() {
      this._map.clear();
   }

   public Object remove(Object var1) {
      float var2;
      if (var1 != null) {
         if (!(var1 instanceof Float)) {
            return null;
         }

         var2 = this.unwrapKey((Float)var1);
      } else {
         var2 = this._map.getNoEntryKey();
      }

      return this._map.remove(var2);
   }

   public Set entrySet() {
      return new AbstractSet(this) {
         final TFloatObjectMapDecorator this$0;

         {
            this.this$0 = var1;
         }

         public int size() {
            return this.this$0._map.size();
         }

         public boolean isEmpty() {
            return this.this$0.isEmpty();
         }

         public Iterator iterator() {
            return new Iterator(this) {
               private final TFloatObjectIterator it;
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
                  this.it = this.this$1.this$0._map.iterator();
               }

               public Entry next() {
                  this.it.advance();
                  float var1 = this.it.key();
                  Float var2 = var1 == this.this$1.this$0._map.getNoEntryKey() ? null : this.this$1.this$0.wrapKey(var1);
                  Object var3 = this.it.value();
                  return new Entry(this, var3, var2) {
                     private Object val;
                     final Object val$v;
                     final Float val$key;
                     final <undefinedtype> this$2;

                     {
                        this.this$2 = var1;
                        this.val$v = var2;
                        this.val$key = var3;
                        this.val = this.val$v;
                     }

                     public boolean equals(Object var1) {
                        return var1 instanceof Entry && ((Entry)var1).getKey().equals(this.val$key) && ((Entry)var1).getValue().equals(this.val);
                     }

                     public Float getKey() {
                        return this.val$key;
                     }

                     public Object getValue() {
                        return this.val;
                     }

                     public int hashCode() {
                        return this.val$key.hashCode() + this.val.hashCode();
                     }

                     public Object setValue(Object var1) {
                        this.val = var1;
                        return this.this$2.this$1.this$0.put(this.val$key, var1);
                     }

                     public Object getKey() {
                        return this.getKey();
                     }
                  };
               }

               public boolean hasNext() {
                  return this.it.hasNext();
               }

               public void remove() {
                  this.it.remove();
               }

               public Object next() {
                  return this.next();
               }
            };
         }

         public boolean add(Entry var1) {
            throw new UnsupportedOperationException();
         }

         public boolean remove(Object var1) {
            boolean var2 = false;
            if (var1 != false) {
               Float var3 = (Float)((Entry)var1).getKey();
               this.this$0._map.remove(this.this$0.unwrapKey(var3));
               var2 = true;
            }

            return var2;
         }

         public boolean addAll(Collection var1) {
            throw new UnsupportedOperationException();
         }

         public void clear() {
            this.this$0.clear();
         }

         public boolean add(Object var1) {
            return this.add((Entry)var1);
         }
      };
   }

   public boolean containsValue(Object var1) {
      return this._map.containsValue(var1);
   }

   public boolean containsKey(Object var1) {
      if (var1 == null) {
         return this._map.containsKey(this._map.getNoEntryKey());
      } else {
         return var1 instanceof Float && this._map.containsKey((Float)var1);
      }
   }

   public int size() {
      return this._map.size();
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public void putAll(Map var1) {
      Iterator var2 = var1.entrySet().iterator();
      int var3 = var1.size();

      while(var3-- > 0) {
         Entry var4 = (Entry)var2.next();
         this.put((Float)var4.getKey(), var4.getValue());
      }

   }

   protected Float wrapKey(float var1) {
      return var1;
   }

   protected float unwrapKey(Float var1) {
      return var1;
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      var1.readByte();
      this._map = (TFloatObjectMap)var1.readObject();
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeByte(0);
      var1.writeObject(this._map);
   }

   public Object put(Object var1, Object var2) {
      return this.put((Float)var1, var2);
   }
}
