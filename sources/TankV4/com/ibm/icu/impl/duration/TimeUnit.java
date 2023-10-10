package com.ibm.icu.impl.duration;

public final class TimeUnit {
   final String name;
   final byte ordinal;
   public static final TimeUnit YEAR = new TimeUnit("year", 0);
   public static final TimeUnit MONTH = new TimeUnit("month", 1);
   public static final TimeUnit WEEK = new TimeUnit("week", 2);
   public static final TimeUnit DAY = new TimeUnit("day", 3);
   public static final TimeUnit HOUR = new TimeUnit("hour", 4);
   public static final TimeUnit MINUTE = new TimeUnit("minute", 5);
   public static final TimeUnit SECOND = new TimeUnit("second", 6);
   public static final TimeUnit MILLISECOND = new TimeUnit("millisecond", 7);
   static final TimeUnit[] units;
   static final long[] approxDurations;

   private TimeUnit(String var1, int var2) {
      this.name = var1;
      this.ordinal = (byte)var2;
   }

   public String toString() {
      return this.name;
   }

   public TimeUnit larger() {
      return this.ordinal == 0 ? null : units[this.ordinal - 1];
   }

   public TimeUnit smaller() {
      return this.ordinal == units.length - 1 ? null : units[this.ordinal + 1];
   }

   public int ordinal() {
      return this.ordinal;
   }

   static {
      units = new TimeUnit[]{YEAR, MONTH, WEEK, DAY, HOUR, MINUTE, SECOND, MILLISECOND};
      approxDurations = new long[]{31557600000L, 2630880000L, 604800000L, 86400000L, 3600000L, 60000L, 1000L, 1L};
   }
}
