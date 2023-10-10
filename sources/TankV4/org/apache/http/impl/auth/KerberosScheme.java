package org.apache.http.impl.auth;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.Oid;

@NotThreadSafe
public class KerberosScheme extends GGSSchemeBase {
   private static final String KERBEROS_OID = "1.2.840.113554.1.2.2";

   public KerberosScheme(boolean var1) {
      super(var1);
   }

   public KerberosScheme() {
      super(false);
   }

   public String getSchemeName() {
      return "Kerberos";
   }

   public Header authenticate(Credentials var1, HttpRequest var2, HttpContext var3) throws AuthenticationException {
      return super.authenticate(var1, var2, var3);
   }

   protected byte[] generateToken(byte[] var1, String var2) throws GSSException {
      return this.generateGSSToken(var1, new Oid("1.2.840.113554.1.2.2"), var2);
   }

   public String getParameter(String var1) {
      Args.notNull(var1, "Parameter name");
      return null;
   }

   public String getRealm() {
      return null;
   }

   public boolean isConnectionBased() {
      return true;
   }
}
