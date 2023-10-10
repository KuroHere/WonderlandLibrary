package org.lwjgl.util.jinput;

import net.java.games.input.Component.Identifier.Key;

final class KeyMap {
   public static Key map(int var0) {
      switch(var0) {
      case 1:
         return Key.ESCAPE;
      case 2:
         return Key._1;
      case 3:
         return Key._2;
      case 4:
         return Key._3;
      case 5:
         return Key._4;
      case 6:
         return Key._5;
      case 7:
         return Key._6;
      case 8:
         return Key._7;
      case 9:
         return Key._8;
      case 10:
         return Key._9;
      case 11:
         return Key._0;
      case 12:
         return Key.MINUS;
      case 13:
         return Key.EQUALS;
      case 14:
         return Key.BACK;
      case 15:
         return Key.TAB;
      case 16:
         return Key.Q;
      case 17:
         return Key.W;
      case 18:
         return Key.E;
      case 19:
         return Key.R;
      case 20:
         return Key.T;
      case 21:
         return Key.Y;
      case 22:
         return Key.U;
      case 23:
         return Key.I;
      case 24:
         return Key.O;
      case 25:
         return Key.P;
      case 26:
         return Key.LBRACKET;
      case 27:
         return Key.RBRACKET;
      case 28:
         return Key.RETURN;
      case 29:
         return Key.LCONTROL;
      case 30:
         return Key.A;
      case 31:
         return Key.S;
      case 32:
         return Key.D;
      case 33:
         return Key.F;
      case 34:
         return Key.G;
      case 35:
         return Key.H;
      case 36:
         return Key.J;
      case 37:
         return Key.K;
      case 38:
         return Key.L;
      case 39:
         return Key.SEMICOLON;
      case 40:
         return Key.APOSTROPHE;
      case 41:
         return Key.GRAVE;
      case 42:
         return Key.LSHIFT;
      case 43:
         return Key.BACKSLASH;
      case 44:
         return Key.Z;
      case 45:
         return Key.X;
      case 46:
         return Key.C;
      case 47:
         return Key.V;
      case 48:
         return Key.B;
      case 49:
         return Key.N;
      case 50:
         return Key.M;
      case 51:
         return Key.COMMA;
      case 52:
         return Key.PERIOD;
      case 53:
         return Key.SLASH;
      case 54:
         return Key.RSHIFT;
      case 55:
         return Key.MULTIPLY;
      case 56:
         return Key.LALT;
      case 57:
         return Key.SPACE;
      case 58:
         return Key.CAPITAL;
      case 59:
         return Key.F1;
      case 60:
         return Key.F2;
      case 61:
         return Key.F3;
      case 62:
         return Key.F4;
      case 63:
         return Key.F5;
      case 64:
         return Key.F6;
      case 65:
         return Key.F7;
      case 66:
         return Key.F8;
      case 67:
         return Key.F9;
      case 68:
         return Key.F10;
      case 69:
         return Key.NUMLOCK;
      case 70:
         return Key.SCROLL;
      case 71:
         return Key.NUMPAD7;
      case 72:
         return Key.NUMPAD8;
      case 73:
         return Key.NUMPAD9;
      case 74:
         return Key.SUBTRACT;
      case 75:
         return Key.NUMPAD4;
      case 76:
         return Key.NUMPAD5;
      case 77:
         return Key.NUMPAD6;
      case 78:
         return Key.ADD;
      case 79:
         return Key.NUMPAD1;
      case 80:
         return Key.NUMPAD2;
      case 81:
         return Key.NUMPAD3;
      case 82:
         return Key.NUMPAD0;
      case 83:
         return Key.DECIMAL;
      case 84:
      case 85:
      case 86:
      case 89:
      case 90:
      case 91:
      case 92:
      case 93:
      case 94:
      case 95:
      case 96:
      case 97:
      case 98:
      case 99:
      case 103:
      case 104:
      case 105:
      case 106:
      case 107:
      case 108:
      case 109:
      case 110:
      case 111:
      case 113:
      case 114:
      case 115:
      case 116:
      case 117:
      case 118:
      case 119:
      case 120:
      case 122:
      case 124:
      case 126:
      case 127:
      case 128:
      case 129:
      case 130:
      case 131:
      case 132:
      case 133:
      case 134:
      case 135:
      case 136:
      case 137:
      case 138:
      case 139:
      case 140:
      case 142:
      case 143:
      case 152:
      case 153:
      case 154:
      case 155:
      case 158:
      case 159:
      case 160:
      case 161:
      case 162:
      case 163:
      case 164:
      case 165:
      case 166:
      case 167:
      case 168:
      case 169:
      case 170:
      case 171:
      case 172:
      case 173:
      case 174:
      case 175:
      case 176:
      case 177:
      case 178:
      case 180:
      case 182:
      case 185:
      case 186:
      case 187:
      case 188:
      case 189:
      case 190:
      case 191:
      case 192:
      case 193:
      case 194:
      case 195:
      case 196:
      case 198:
      case 202:
      case 204:
      case 206:
      case 212:
      case 213:
      case 214:
      case 215:
      case 216:
      case 217:
      case 218:
      default:
         return Key.UNKNOWN;
      case 87:
         return Key.F11;
      case 88:
         return Key.F12;
      case 100:
         return Key.F13;
      case 101:
         return Key.F14;
      case 102:
         return Key.F15;
      case 112:
         return Key.KANA;
      case 121:
         return Key.CONVERT;
      case 123:
         return Key.NOCONVERT;
      case 125:
         return Key.YEN;
      case 141:
         return Key.NUMPADEQUAL;
      case 144:
         return Key.CIRCUMFLEX;
      case 145:
         return Key.AT;
      case 146:
         return Key.COLON;
      case 147:
         return Key.UNDERLINE;
      case 148:
         return Key.KANJI;
      case 149:
         return Key.STOP;
      case 150:
         return Key.AX;
      case 151:
         return Key.UNLABELED;
      case 156:
         return Key.NUMPADENTER;
      case 157:
         return Key.RCONTROL;
      case 179:
         return Key.NUMPADCOMMA;
      case 181:
         return Key.DIVIDE;
      case 183:
         return Key.SYSRQ;
      case 184:
         return Key.RALT;
      case 197:
         return Key.PAUSE;
      case 199:
         return Key.HOME;
      case 200:
         return Key.UP;
      case 201:
         return Key.PAGEUP;
      case 203:
         return Key.LEFT;
      case 205:
         return Key.RIGHT;
      case 207:
         return Key.END;
      case 208:
         return Key.DOWN;
      case 209:
         return Key.PAGEDOWN;
      case 210:
         return Key.INSERT;
      case 211:
         return Key.DELETE;
      case 219:
         return Key.LWIN;
      case 220:
         return Key.RWIN;
      case 221:
         return Key.APPS;
      case 222:
         return Key.POWER;
      case 223:
         return Key.SLEEP;
      }
   }
}
