package gnu.trove.impl.unmodifiable;

import gnu.trove.list.TByteList;
import java.util.RandomAccess;

public class TUnmodifiableRandomAccessByteList extends TUnmodifiableByteList implements RandomAccess {
   private static final long serialVersionUID = -2542308836966382001L;

   public TUnmodifiableRandomAccessByteList(TByteList var1) {
      super(var1);
   }

   public TByteList subList(int var1, int var2) {
      return new TUnmodifiableRandomAccessByteList(this.list.subList(var1, var2));
   }

   private Object writeReplace() {
      return new TUnmodifiableByteList(this.list);
   }
}
