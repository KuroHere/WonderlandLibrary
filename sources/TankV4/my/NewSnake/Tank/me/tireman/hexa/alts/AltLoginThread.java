package my.NewSnake.Tank.me.tireman.hexa.alts;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import java.net.Proxy;
import my.NewSnake.Tank.Snake;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Session;

public final class AltLoginThread extends Thread {
   private Minecraft mc = Minecraft.getMinecraft();
   private String status;
   private final String password;
   private final String username;

   private Session createSession(String var1, String var2) {
      YggdrasilAuthenticationService var3 = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
      YggdrasilUserAuthentication var4 = (YggdrasilUserAuthentication)var3.createUserAuthentication(Agent.MINECRAFT);
      var4.setUsername(var1);
      var4.setPassword(var2);

      try {
         var4.logIn();
         return new Session(var4.getSelectedProfile().getName(), var4.getSelectedProfile().getId().toString(), var4.getAuthenticatedToken(), "mojang");
      } catch (AuthenticationException var6) {
         var6.printStackTrace();
         return null;
      }
   }

   public void run() {
      if (this.password.equals("")) {
         this.mc.session = new Session(this.username, "", "", "mojang");
         this.status = EnumChatFormatting.GREEN + "Logged in. (" + this.username + " - offline name)";
      } else {
         this.status = EnumChatFormatting.YELLOW + "Logging in...";
         Session var1 = this.createSession(this.username, this.password);
         if (var1 == null) {
            this.status = EnumChatFormatting.RED + "Login failed!";
         } else {
            AltManager var2 = Snake.instance.altManager;
            AltManager.lastAlt = new Alt(this.username, this.password);
            this.status = EnumChatFormatting.GREEN + "Logged in. (" + var1.getUsername() + ")";
            this.mc.session = var1;
         }

      }
   }

   public String getStatus() {
      return this.status;
   }

   public AltLoginThread(String var1, String var2) {
      super("Alt Login Thread");
      this.username = var1;
      this.password = var2;
      this.status = EnumChatFormatting.GRAY + "Waiting...";
   }

   public void setStatus(String var1) {
      this.status = var1;
   }
}
