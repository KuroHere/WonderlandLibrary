package com.ibm.icu.text;

import com.ibm.icu.util.CompactByteArray;
import com.ibm.icu.util.CompactCharArray;

class DecompData {
   static final int MAX_CANONICAL = 21754;
   static final int MAX_COMPAT = 11177;
   static final int DECOMP_MASK = 32767;
   static final int DECOMP_RECURSE = 32768;
   static final short BASE = 0;
   static final CompactCharArray offsets = new CompactCharArray("\u0000ࠀꖥ\u0005\u0000 @`\u0080 Àà\u0000ĀĠŀŠƀ\u0000\u0000\u0000ƠǀǠ\u0000\u0000ȀȠɀɠʀʠˀˠ̠̀\u0000\u0000̀͠ꖥ\u0004\u0000\u0380ꖥ\u0004\u0000Π\u0000π\u0000\u0000Ϡꖥ\u0012\u0000ЀР\u0000\u0000\u0000р\u0000\u0000ѠҀꖥ\u0007\u0000Ҡ\u0000Ӏ\u0000Ӡ\u0000\u0000\u0000Ԁ\u0000\u0000\u0000Ԡ\u0000\u0000\u0000Հ\u0000\u0000\u0000\u0560\u0000\u0000ր\u0000\u0000\u0000֠׀\u0000נ\u0000\u0600ؠـ٠\u0000\u0000\u0000ڀꖥn\u0000ڠۀ۠܀ܠ݀ݠހޠ߀ߠࠀࠠࡀ\u0860\u0880ࢠ\u08c0\u08e0ऀठी\u0000\u0000ॠ\u0980ঠীৠ\u0a00ਠ\u0000ੀ\u0a60\u0a80ઠીૠ\u0000\u0b00\u0000ଠꖥ\t\u0000ୀୠ\u0b80\u0ba0ீꖥL\u0000\u0be0\u0000\u0000\u0c00ఠీౠ\u0c80ಠೀೠ\u0000\u0d00ഠീൠ\u0d80චව\u0de0\u0000\u0e00ภเ\u0e60\u0000\u0000\u0000\u0e80\u0ea0ເ\u0ee0ༀ༠ཀའྀྠ࿀\u0fe0ကဠ၀ၠꖥب\u0000ႀႠჀრᄀᄠᅀᅠᆀᆠꖥ\u0006\u0000ᇀᇠሀሠቀበኀአዀዠጀጠፀ፠ᎀᎠᏀᏠ᐀ᐠᑀᑠᒀᒠ\u0000ᓀᓠᔀᔠᕀᕠᖀᖠᗀᗠᘀᘠᙀᙠ ", "\u0000ᚠꖥ \u0000\u0005ꖥ\u0007\u0000\n\u0000\u0011ꖥ\u0004\u0000\u0016\u0000\u0000\u001d!&-\u0000\u000029=\u0000CO[\u0000\u2bae\u2bb6\u2bbe\u2bc6\u2bce\u2bd6\u0000\u2bde\u2be6\u2bee\u2bf6\u2bfeⰆⰎⰖⰞ\u0000ⰦⰮⰶⰾⱆⱎ\u0000\u0000ⱖⱞⱦⱮⱶ\u0000\u0000ⱾⲆⲎⲖⲞⲦ\u0000ⲮⲶⲾⳆⳎⳖⳞ⳦ⳮ\u0000\u2cf6⳾ⴆⴎⴖⴞ\u0000\u0000\u2d26\u2d2eⴶⴾⵆ\u0000ⵎⵖⵞⵦ\u2d6e\u2d76\u2d7eⶆⶎⶖ\u2d9eⶦⶮⶶⶾⷆⷎ\u0000\u0000ⷖⷞⷦⷮⷶⷾ⸆⸎⸖⸞⸦⸮⸶\u2e3e\u2e46\u2e4e\u2e56\u2e5e\u2e66\u2e6e\u0000\u0000\u2e76\u2e7e⺆⺎⺖⺞⺦⺮⺶\u0000fn⺾⻆⻎⻖\u0000⻞⻦⻮\u2ef6\u2efe⼆v~\u0000\u0000⼎⼖⼞⼦⼮⼶\u0086\u0000\u0000⼾⽆⽎⽖⽞⽦\u0000\u0000⽮⽶⽾⾆⾎⾖⾞⾦⾮⾶⾾⿆⿎\u2fd6\u2fde\u2fe6\u2fee⿶\u0000\u0000\u2ffe〆『〖〞〦〮〶〾うぎざぞてのぶまゆゎゖゞウギ\u008dザゾꖥ\r\u0000テノꖥ\u0013\u0000\u0093\u009f«¶¾ÆÎÖÞブマユヮヶヾㄆㄎㄗㄣ\u312fㄻㅇㅓㅟㅫ\u0000ㅷㆃ\u318f㆛ㆦㆮ\u0000\u0000ㆶ\u31be㇆㇎㇖㇞\u31e7ㇳㇾ㈆㈎\u0092\u009eª㈖㈞\u0000\u0000㈦㈮㈷㉃㉎㉖㉞㉦㉮㉶㉾㊆㊎㊖㊞㊦㊮㊶㊾㋆㋎㋖㋞㋦㋮㋶㋾㌆㌎㌖㌞㌦㌮㌶㌾㍆\u0000\u0000㍎㍖ꖥ\u0006\u0000㍞㍦㍮㍶㍿㎋㎗㎣㎮㎶㎿㏋㏖㏞ꖥ\u001c\u0000åéqíñõùýāꖥ\u001f\u0000ĆĎĖĞĦĮ\u0000\u0000ĵ}\u008dĹĽꖥ\u001b\u0000㏥㏩\u0000㏭㏲ꖥ/\u0000㏹ꖥ\u0005\u0000ł\u0000\u0000\u0000㏽ꖥ\u0005\u0000&됂㐊㐑㐖㐞㐦\u0000㐮\u0000㐶㐾㑇ꖥ\u0019\u0000㑒㑚㑢㑪㑲㑺㒃ꖥ\u0019\u0000㒎㒖㒞㒦㒮\u0000ŉōő뒶뒾ŕřꖥ\u0019\u0000ŝšťꖥ\r\u0000㓆㓎\u0000㓖\u0000\u0000\u0000㓞ꖥ\u0004\u0000㓦㓮㓶ꖥ\n\u0000㓾ꖥ\u001f\u0000㔆ꖥ\u0016\u0000㔎㔖\u0000㔞\u0000\u0000\u0000㔦ꖥ\u0004\u0000㔮㔶㔾ꖥ\u0017\u0000㕆㕎ꖥ\t\u0000㕖㕞ꖥ\r\u0000㕦㕮㕶㕾\u0000\u0000㖆㖎\u0000\u0000㖖㖞㖦㖮㖶㖾\u0000\u0000㗆㗎㗖㗞㗦㗮\u0000\u0000㗶㗾㘆㘎㘖㘞㘦㘮㘶㘾㙆㙎\u0000\u0000㙖㙞ꖥ\r\u0000Ūꖥ\u001a\u0000㙦㙮㙶㙾㚆ꖥ.\u0000ŲźƂƊꖥ\u0007\u0000㚎\u0000㚖ꖥ\u0010\u0000㚞ꖥ\u0015\u0000㚦ꖥ\u0007\u0000㚮\u0000\u0000㚶ꖥ#\u0000㚾㛆㛎㛖㛞㛦㛮㛶ꖥ\u000b\u0000㛾㜆ꖥ\u000f\u0000㜎㜖\u0000㜞ꖥ\u0013\u0000㜦\u0000\u0000㜮ꖥ\"\u0000㜶㜾㝆\u0000\u0000㝎ꖥ\t\u0000㝖\u0000\u0000㝞㝦ꖥ\u000f\u0000㝮㝶ꖥ\u0016\u0000㝾ꖥ\u0015\u0000㞆㞎㞖ꖥ\u001b\u0000㞞ꖥ\u0017\u0000㞦ꖥ\u0006\u0000㞮㞶\u0000㞾㟇ꖥ\u001e\u0000㟒㟚㟢ꖥ-\u0000㟪\u0000㟲㟻㠆ꖥ\u0014\u0000ƒꖥ\u001f\u0000ƚꖥ(\u0000Ƣƪꖥ\u000e\u0000Ʊꖥ\u0016\u0000㠎ꖥ\t\u0000㠖ꖥ\u0004\u0000㠞ꖥ\u0004\u0000㠦ꖥ\u0004\u0000㠮ꖥ\f\u0000㠶ꖥ\t\u0000㠾\u0000㡆㡎Ʒ㡖ǃꖥ\u0007\u0000㡞ꖥ\u0011\u0000㡦ꖥ\t\u0000㡮ꖥ\u0004\u0000㡶ꖥ\u0004\u0000㡾ꖥ\u0004\u0000㢆ꖥ\f\u0000㢎ꖥ\f\u0000㢖ꖥ\u0019\u0000㢞㢦㢮㢶㢾㣆㣎㣖㣟㣫㣶㣾㤆㤎㤖㤞㤦㤮㤶㤾㥇㥓㥟㥫㥶㥾㦆㦎㦗㦣㦮㦶㦾㧆㧎㧖㧞㧦㧮㧶㧾㨆㨎㨖㨞㨦㨯㨻㩆㩎㩖㩞㩦㩮㩶㩾㪇㪓㪞㪦㪮㪶㪾㫆㫎㫖㫞㫦㫮㫶㫾㬆㬎㬖㬞㬦㬯㬻㭇㭓㭟㭫㭷㮃㮎㮖㮞㮦㮮㮶㮾㯆㯏㯛㯦㯮㯶㯾㰆㰎㰗㰣㰯㰻㱇㱓㱞㱦㱮㱶㱾㲆㲎㲖㲞㲦㲮㲶㲾㳆㳏㳛㳧㳳㳾㴆㴎㴖㴞㴦㴮㴶㴾㵆㵎㵖㵞㵦㵮㵶㵾㶆㶎㶖㶞㶦㶮㶶㶾㷆㷎㷖㷞㷦ǎ뷮ꖥ\u0004\u0000㷶㷾㸆㸎㸗㸣㸯㸻㹇㹓㹟㹫㹷㺃㺏㺛㺧㺳㺿㻋㻗㻣㻯㻻㼆㼎㼖㼞㼦㼮㼷㽃㽏㽛㽧㽳㽿㾋㾗㾣㾮㾶㾾㿆㿎㿖㿞㿦㿯㿻䀇䀓䀟䀫䀷䁃䁏䁛䁧䁳䁿䂋䂗䂣䂯䂻䃇䃓䃞䃦䃮䃶䃿䄋䄗䄣䄯䄻䅇䅓䅟䅫䅶䅾䆆䆎䆖䆞䆦䆮ꖥ\u0006\u0000䆶䆾䇇䇓䇟䇫䇷䈃䈎䈖䈟䈫䈷䉃䉏䉛䉦䉮䉷䊃䊏䊛\u0000\u0000䊦䊮䊷䋃䋏䋛\u0000\u0000䋦䋮䋷䌃䌏䌛䌧䌳䌾䍆䍏䍛䍧䍳䍿䎋䎖䎞䎧䎳䎿䏋䏗䏣䏮䏶䏿䐋䐗䐣䐯䐻䑆䑎䑗䑣䑯䑻\u0000\u0000䒆䒎䒗䒣䒯䒻\u0000\u0000䓆䓎䓗䓣䓯䓻䔇䔓\u0000䔞\u0000䔧\u0000䔳\u0000䔿䕊䕒䕛䕧䕳䕿䖋䖗䖢䖪䖳䖿䗋䗗䗣䗯䗺䘂䘊䘒䘚䘢䘪䘲䘺䙂䙊䙒䙚䙢\u0000\u0000䙫䙷䚀䚔䚨䚼䛐䛤䛻䜇䜐䜤䜸䝌䝠䝴䞋䞗䞠䞴䟈䟜䟰䠄䠛䠧䠰䡄䡘䡬䢀䢔䢫䢷䣀䣔䣨䣼䤐䤤䤻䥇䥐䥤䥸䦌䦠䦴䧊䧒䧛䧦䧯\u0000䧺䨃䨎䨖䨞䨦䨮ǖ䨵ǖǞ쨺䩃䩎䩗\u0000䩢䩫䩶䩾䪆䪎䪖쪞쪦쪮䪶䪾䫇䫓\u0000\u0000䫞䫧䫲䫺䬂䬊\u0000쬒쬚쬢䬪䬲䬻䭇䭒䭚䭢䭫䭶䭾䮆䮎䮖쮞쮦䮭\u0000\u0000䮳䮾䯇\u0000䯒䯛䯦䯮䯶䯾䰆찍Ǧ\u0000찑찕ꖥ\t\u0005ꖥ\u0006\u0000ǭꖥ\u0005\u0000ǲꖥ\f\u0000ǹǾǻꖥ\b\u0000\u0005\u0000\u0000\u0000Ȇȏ\u0000Țȣꖥ\u0004\u0000Ȯ\u0000ȶꖥ\t\u0000ȾɆꖥ&\u0000ɍ\u0000\u0000\u0000Iɑɕəɝɡɥɩɭɱɵ\u0089ɍ9\u001d!Iɑɕəɝɡɥɩɭɱɵꖥ\u0019\u0000ɺꖥ\u0017\u0000ʃʏʙʞ\u0000ʧʳʽ\u0000˂ˉˍˍˍåˑeeu}\u0000Í˖\u0000\u0000˝ˡɹɹɹ\u0000\u0000˦˯˺\u0000\u0095\u0000䰙\u0000\u0095\u0000䰝䰢́ʙ\u0000̅˱˅\u0000˩=̉̍̑̕mꖥ\u0019\u0000̧̛̳̿͋͗ͣͯͻ·ΓΟBeΪγξρφϏϘϮϱ϶Ͽuʙ\u0091˩mЊГОСЦЯиюĹіџ}ʉ©ѩꖥ\u001a\u0000䰪䰲ꖥ\u0012\u0000䰺ꖥ\u001e\u0000䱂䱊䱒ꖥ\u0014\u0000䱚ꖥ\u0004\u0000䱢\u0000\u0000䱪ꖥ\u0017\u0000䱲\u0000䱺ꖥ\u0005\u0000Ѯѷ\u0000҂ҋꖥ\u0010\u0000䲂\u0000\u0000䲊\u0000\u0000䲒\u0000䲚ꖥ\u0016\u0000䲢\u0000䲪ꖥ\n\u0000䲲䲺䳂䳊䳒\u0000\u0000䳚䳢\u0000\u0000䳪䳲ꖥ\u0006\u0000䳺䴂\u0000\u0000䴊䴒\u0000\u0000䴚䴢ꖥ\"\u0000䴪䴲䴺䵂ꖥ\u0010\u0000䵊䵒䵚䵢ꖥ\u0006\u0000䵪䵲䵺䶂ꖥ\u001b\u0000䶉䶍ꖥ\u0015\u00009\u001d!IɑɕəɝɡҖҞҦҮҶҾӆӎӖӞӦӯӻԇԓԟ\u052bԷՃՏ\u0558լրּ֔֨אפ\u05f8،ؠضؾنَٖٞ٦ٮٶٿڋڗڣگڻۇۓ۟۫۷܃\u070fܛܧܳܿ\u074bݗݣݯݻއޓޟޫ\u07b7߃ߏߛߧ߳\u07ffࠋࠗࠣ\u082f࠹́ʙ\u0091˱˅࠽ˍeiࡁu˩Íࡅ˝ˡɹ˥˭ࡉρࡍϱࡑ\u0095\u0011ܑʉ©̅݁ˉåmqݽ}ѩ\u0089=\u07b9߅í\u008dߩʹСýĹā¡ɍꖥ4\u0000ࡕꖥ\u0013\u0000࡙ꖥ\f\u0000\u085d\u0861\u0865\u0869\u086d\u0871\u0875\u0879\u087d\u0881\u0885\u0889\u088d\u0891\u0895\u0899\u089d\u08a1ࢥࢩ\u08ad\u08b1\u08b5\u08b9\u08bd\u08c1\u08c5\u08c9\u08cd\u08d1\u08d5\u08d9\u08dd\u08e1ࣱࣩ࣭ࣹࣥࣵࣽँअउऍऑकङझडथऩभऱवहऽुॅॉ्॑ॕख़ढ़ॡ॥३७ॱॵॹॽঁঅউ\u098d\u0991কঙঝডথ\u09a9ভ\u09b1\u09b5হঽু\u09c5\u09c9্\u09d1\u09d5\u09d9ঢ়ৡ\u09e5৩৭ৱ৵৹\u09fdਁਅਉ\u0a0d\u0a11ਕਙਝਡਥ\u0a29ਭ\u0a31ਵਹ\u0a3dੁ\u0a45\u0a49੍ੑ\u0a55ਖ਼\u0a5d\u0a61\u0a65੩੭ੱੵ\u0a79\u0a7dઁઅઉઍઑકઙઝડથ\u0aa9ભ\u0ab1વહઽુૅૉ્\u0ad1\u0ad5\u0ad9\u0addૡ\u0ae5૩૭૱\u0af5\u0af9\u0afdଁଅଉ\u0b0d\u0b11କଙଝଡଥ\u0b29ଭ\u0b31ଵହଽୁ\u0b45\u0b49୍\u0b51\u0b55\u0b59ଢ଼ୡ\u0b65୩୭ୱ୵\u0b79\u0b7d\u0b81அஉ\u0b8d\u0b91கங\u0b9d\u0ba1\u0ba5ன\u0badறꖥ\n\u0000\u0005ꖥ5\u0000வ\u0000\u08b9ஹ\u0bbdꖥ\u0011\u0000䶒\u0000䶚\u0000䶢\u0000䶪\u0000䶲\u0000\u4dba\u0000䷂\u0000䷊\u0000䷒\u0000䷚\u0000䷢\u0000䷪\u0000\u0000䷲\u0000䷺\u0000丂ꖥ\u0006\u0000上丒\u0000业丢\u0000个串\u0000为乂\u0000乊乒ꖥ\u0016\u0000乚ꖥ\u0006\u0000ூொ\u0000乢ꖥ\r\u0000乪\u0000乲\u0000乺\u0000亂\u0000亊\u0000互\u0000亚\u0000亢\u0000亪\u0000亲\u0000人\u0000仂\u0000\u0000今\u0000仒\u0000仚ꖥ\u0006\u0000仢仪\u0000仲仺\u0000伂伊\u0000伒会\u0000伢伪ꖥ\u0016\u0000伲\u0000\u0000伺佂佊佒\u0000\u0000\u0000佚ꖥ\u0012\u0000\u0bd1\u0bd5\u0bd9\u0bdd\u0be1\u0be5௩௭௱௵௹\u0bfdఁఅఉ\u0c0d\u0c11కఙఝడథ\u0c29భఱవహఽు\u0c45\u0c49్\u0c51ౕౙ\u0c5dౡ\u0c65౩౭\u0c71\u0c75౹౽\u0c81ಅಉ\u0c8d\u0c91ಕಙಝಡಥ\u0ca9ಭಱವಹಽು\u0cc5\u0cc9್\u0cd1ೕ\u0cd9\u0cddೡ\u0ce5೩೭ೱ\u0cf5\u0cf9\u0cfd\u0d01അഉ\u0d0d\u0d11കങഝഡഥഩഭറവഹഽു\u0d45\u0000\u0000\u0000\u085d\u0875\u0d49്\u0d51\u0d55\u0d59\u0d5d\u086dൡ\u0d65൩൭\u087d൳ൿඋ\u0d97ඣදර\u0dc7ීෟ\u0deb\u0df7ฃฏธฬเ๔\u0e68\u0e7c\u0e90\u0ea4ຸ໌\u0ee0\u0ef4༈༜༰\u0000\u0000\u0000ཇནཟཫཷྃྏྛྦྷླ྿࿋࿗\u0fe3\u0fef\u0ffbဇဓဟါ့၃၏ၛၧၳၿႋ႗ႣႯႻჇდჟძꖥ\u001c\u0000\u0bd1\u0bdd௩௱\u0c11కడ\u0c29భవహఽు\u0c45พาๆ๚\u0e6eຂຖສ\u0ebe໒\u0ee6\u0efa༎ెꖥ\u0004\u0000\u085d\u0875\u0d49്ཹ྅ྑ\u0889ྩ\u08b9ঁ\u09b1ভঅ\u0af5\u08d9ॹပအိ္၅ၑၝၩჵჹࣱჽᄁᄅᄉᄍვᄑᄕ\u0d51\u0d55\u0d59ᄙᄝᄡᄥႍ႙ႥႱႽᄩꖥ\u000f\u0000ᄮᄶᄾᅆᅎᅖᅞᅦᅮᅷᆃᆏꖥ\u0004\u0000ᆙᆝᆡᆥᆩᆭᆱᆵᆹᆽᇁᇅᇉᇍᇑᇕᇙᇝᇡᇥᇩᇭᇱᇵᇹᇽሁህሉልሑሕሙምሡሥሩርሱስሹሽቁቅ\u1249ቍቑ\u0000ቔቬኀኛኤ\u12bfዋዔደጇጓጟጨጼፐ፤፸ᎌᎠᎴᏒᏘᏴᐐᏠᐨᑄᑠᑷᒃᒌᒠᒴᓌᓧᓳᓼᔓᔟᐺᔪᔳᔿᕈᕤᕸᖐᖬᗃᗏᗘᗴᘈᘧᘰᙋᙔᙫᙴᚈᚠᚴᛌᛢᛨᜃᜏ\u1718ᜯ\u173bᝇᝐᝨ\u177eងឣឬ\u13fcោ៛៧៰᠆᠌ᠠᠺᡀᐛᡞᡦᡮᡶ\u187eᢆᢎᢖᢞᢦ\u18afᢻᣇᣓᣟᣫ\u18f7ᤃᤏᤛᤧᤳ\u193f᥋ᥗᥣ\u196e\u1976\u197fᦊᦒꖥ\u0004\u0000ᦚᦢᦪᦲᦸ\u19ce᧖᧞᧦᧮᧶˾᧾ᨇᨐᨦᨮᨶᨾᩆᩎᩖ\u1a5fᩫ᩷᪃\u1a8e᪖\u1a9e᪦\u1aae\u1ab6\u1abe\u1ac6\u1ace\u1ad6\u1adf\u1aeb\u1ae2\u1af7ᬃᬏᬆᬛᬧᬰᥦᭇ᭓᭟᭫᭴ᮌ᮪\u008a᮲ᮺᯂᯊᯒᯚᯢᯪ᯲\u1bfaᰂᰊᰒᰚᰢᰪᰰ᱆ᱎ᱖ᱜᱳ᱾\u1c86\u1c8e\u1c96\u1c9e\u1ca6\u1cae\u1cb6᪒\u1cbe᳇᳣᳒᳚ᳯ\u1cfaᴀᴗᴢᴪᴲᴺ\u0000\u0000ᵂᵊᵒᵚᵢᵪᵲᵺᶂᶋᶗᶣᶯᶻ᷇ᷓᷟ\u1deb\u1df7ḃḏḛḧḳḿṋṗṣṯṻẇ\u0000佡佥佩佭佱併佹佽侁侅侉侍侑侕侙依価侥侩侭侱侵侹侽俁俅俉俍俑俕俙保信俥俩俭俱俵俹俽倁倅倉倍們倕候倝倡倥倩倭倱倵倹倽偁偅偉偍偑偕偙偝偡健偩偭偱偵偹偽傁傅傉傍傑傕備傝傡傥傩傭傱債傹傽僁僅僉働僑僕僙僝僡僥僩僭僱僵價僽儁儅儉儍儑儕儙儝儡儥儩儭儱儵儹儽允充光免兑兕兙兝兡入兩六共兵兹兽冁内冉再冑冕写冝冡冥冩冭冱况冹冽凁凅凉凍凑凕凙凝凡凥凩凭凱凵凹函刁刅刉刍刑刕则初刡別利刭刱刵刹刽剁剅剉前剑剕剙剝剡剥剩剭剱創剹剽劁劅劉劍劑劕劙劝务劥助劭励劵効劽勁勅勉勍勑動務勝勡勥勩勭勱勵勹勽匁包匉匍匑匕匙匝匡匥匩匭匱匵匹匽十卅卉卍卑单卙卝卡卥卩卭危卵卹卽厁厅厉厍厑厕\u0000\u0000厙\u0000厝\u0000\u0000厡厥厩厭厱厵厹厽叁叅\u0000叉\u0000反\u0000\u0000发叕\u0000\u0000\u0000叙叝叡句ꖥ\u0012\u0000ẒẚẢẗẫẶẶꖥ\f\u0000ẾỆỎỖỞꖥ\u0005\u0000只\u0000史ụ̉̕ứửựỵỹỽɥ叺吂吋吗吢吪吲吺呂告呒呚呢\u0000呪呲呺咂咊\u0000咒\u0000咚咢\u0000咪咲\u0000咺哂哊哒哚哢哪哲哺ἂἉἉꖥ\u0004Ἅꖥ\u0004ἑꖥ\u0004ἕꖥ\u0004Ἑꖥ\u0004Ἕꖥ\u0004ἡꖥ\u0004ἥꖥ\u0004Ἡꖥ\u0004Ἥꖥ\u0004ἱꖥ\u0004ἵꖥ\u0004ἹἽἽὁὁὅὅὉὉὍὍὑὑꖥ\u0004ὕꖥ\u0004Ὑꖥ\u0004Ὕꖥ\u0004ὡὥὥꖥ\u0004ὩὮὮꖥ\u0004ήꖥ\u0004όώώᾂᾂꖥ!\u0000ꖥ\u0004ᾉƁƁᾍᾍᾑᾑƂᾕᾕᾙᾙᾝᾝꖥ\u0004ᾡᾥᾥᾫᾫᾷᾷῃῃ῏῏ΊΊῧῧῳῳῳ\u1fff\u1fff\u1fffꖥ\u0004 \u200f‛‧\u1fff″‾⁆⁎⁖⁞\u2066\u206e⁶⁾₆₎ₖ\u209e₦₮₶₾\u20c6\u20c2\u20ce⃖⃞⃦⃮\u20f6\u20fe℆ℎ№℞Ω℮ℶℾⅆⅎ⅖⅞ⅦⅮⅶⅾↆ\u218e↖↞↦↮↶↾⇆⇎⇖⇞⇦⇮⇶⇾∆∎∖∞∦∮∶\u20ca⃒∾≆\u202e≎≖≞≦≮≶≾⊆⊎⊖₺⊞⊦∲⊮⊚⊶⊾⋆⋏⋛⋧⋳⋿⌋⌗⌣‧⌯\u1fff″⌺⍂⁖⍊⁞\u2066⍒⍚₆⍢₎ₖ⍪⍲₦⍺₮₶↖↞↶↾⇆⇦⇮⇶⇾∞∦∮⎂∾⎊⎒≦⎚≮≶⋆⎢⎪∲⎲⊮⊚\u200f‛⎻‧⏇‾⁆⁎⁖⏒\u206e⁶⁾₆⏚₦₾\u20c6\u20c2\u20ce⃦⃮⃖\u20f6\u20fe℆ℎ⏢№℞Ω℮ℶℾⅎ⅖⅞ⅦⅮⅶⅾↆ\u218e↦↮⇎⇖⇞⇦⇮∆∎∖∞⏪∶\u20ca⃒∾≎≖≞≦⏲≾⊆\u23fa₺⊞⊦∲≺‧⏇⁖⏒₆⏚₦␂℆␊␒␚⇦⇮∞≦⏲∲≺␣\u242f\u243b⑆\u244e\u2456\u245e⑦⑮⑶⑾⒆⒎⒖•⒞‖⒦⊪⒮ⒶⒾⓆⓎⓖⓞ␒ⓦ⓮⓶⓾⑆\u244e\u2456\u245e⑦⑮⑶⑾⒆⒎⒖•⒞‖⒦⊪⒮ⒶⒾⓆⓎⓖⓞ␒ⓦ⓮⓶⓾Ⓨⓖⓞ␒␊␚ⅆ⃮\u20f6\u20feⓎⓖⓞⅆⅎ┆┆ꖥ\u0012\u0000┏┛┛┧┳┿╋╗\u20c7\u20c7╣╯╻▇▓▟▟▫▷▷◃◃●◛◛◧◳◳◿◿☋☗☗☣☣☯☻♇♓♓♟♫♷⚃⚏⚏⚛⚧⚳⚿⛋⛗⛗⛣⛣⛯⛯⛻\u20cb✇✓∷⃓✟\u0000\u0000✫✷❃❏❛❧❧❳❿➋➗➗➣➯➻⟇⟓⟟⟫⟷⠃⠏⠛⠧⠳⠿⡋⡗⊟⡣⡯⡻⢇⢓⚛⚳⢟⢫⢷⣃⣏⣛⣏⢷⣧⣳⣿⤋⤗⣛♇●⤣⤯ꖥ(\u0000⤻⥇⥐⥤⥸⦌⦠⦴⧈⧟⧨⨴ꖥ\u0014\u0000Ǻ⩙⩝⩡⩡ɱɵ⩥⩩⩭⩱⩵⩹⩽⪁⪅⪉⪍⪑⪕⪙ꖥ\u0004\u0000ꖥ\u0004ȶ⩡⩡⩡⪝⪡ǹ\u0000⪥⪩Ƚȭ⩙ɱɵ⩥⩩⩭⩱⪭⪱⪵ɥ⪹⪽⫁ɭ\u0000⫅⫉⫍⫑ꖥ\u0004\u0000⫖⫞⋎\u0000⋚\u0000⋦␢⋲\u242e⋾\u243a⌊⫦⫮⫶⫽⬂⬂⬊⬊⬒⬒⬚⬚ꖥ\u0004ᾪűűꖥ\u0004‽⬡⬡ꖥ\u0004\u206dꖥ\u0004\u209dꖥ\u0004―ꖥ\u0004‡ꖥ\u0004⁑⦅⦅⊵⊵⊽⊽〈〈ꖥ\u0004⃭ꖥ\u0004␑ꖥ\u0004ℍꖥ\u0004ℝꖥ\u0004ℽꖥ\u0004⅍ꖥ\u0004⅕ꖥ\u0004Ⅵꖥ\u0004ⅵꖥ\u0004↥ꖥ\u0004⇅ꖥ\u0004⇩ꖥ\u0004\u202dꖥ\u0004≍ꖥ\u0004≽ŹŹᾥᾥꖥ\u0004Ɖ⬧⬧⬳⬳⬿⬿⩆⩆ꖥ\u0004\u0000ȭ⭉⪭⫉⫍⪱\u2b4dɱɵ⪵ɥ⪝⪹ǹʅɍ9\u001d!Iɑɕəɝɡ⪩⪥⪽ɭ⫁Ƚ⫑࠹́ʙ\u0091˱˅࠽ˍeiࡁu˩Íࡅ˝ˡɹ˥˭ࡉρࡍϱࡑ\u0095⭑⫅⭕⭙⩡\u2b5d\u0011ܑʉ©̅݁ˉåmqݽ}ѩ\u0089=\u07b9߅í\u008dߩʹСýĹā¡⩥\u2b61⩩\u2b65\u0000\u0000\u2b69⪍⪑⪡\u2b6dቑቹᗹ\u2b71ᘙው\u2b75Ꭵ\u175dጱቡᆙᆝᆡᆥᆩᆭᆱᆵᆹᆽᇁᇅᇉᇍᇑᇕᇙᇝᇡᇥᇩᇭᇱᇵᇹᇽሁህሉልሑሕሙምሡሥሩርሱስሹሽቁቅኅ\u0bc5்ಝ\u0bd1\u0bd5\u0bd9\u0bdd\u0be1\u0be5௩௭௱௵௹\u0bfdఁఅఉ\u0c0d\u0c11కఙఝడథ\u0c29భఱవహఽు\u0c45\u0000\u0000\u0000\u0c49్\u0c51ౕౙ\u0c5d\u0000\u0000ౡ\u0c65౩౭\u0c71\u0c75\u0000\u0000౹౽\u0c81ಅಉ\u0c8d\u0000\u0000\u0c91ಕಙ\u0000\u0000\u0000\u2b79\u2b7d\u2b81\u0016\u2b85\u2b89\u2b8d\u0000\u2b91\u2b95\u2b99\u2b9d\u2ba1\u2ba5\u2ba9ꖥ\u0011\u0000");
   static final String contents = "\uffff  ̈a ̄23 ́μ ̧1o1⁄41⁄23⁄4IJijL·l·ʼnsDŽDždžLJLjljNJNjnjhɦrɹɻʁwy ̆ ̇ ̊ ̨ ̃ ̋ɣxʕ ͅβθΥφπκρςեւاٴوٴۇٴيٴําໍາຫນຫມ་ྲཱྀླཱྀaʾ ̓ ͂ ̔‐ ̳...′′′′′‵‵‵‵‵!! ̅?!!?056789+−=()Rsa/ca/sC°Cc/oc/uƐ°FgHħNoPQSMTELTMBeאבגד1⁄32⁄31⁄52⁄53⁄54⁄51⁄65⁄61⁄83⁄85⁄87⁄8IIIIIIVVIVIIVIII\u0000IXXIXIIiiiiiivviviiviii\u0000ixxixiim∫∫∫∫∫∮∮∮∮∮1011121314151617181920(1)(2)(3)(4)(5)(6)(7)(8)(9)(10)\u0000(11)\u0000(12)\u0000(13)\u0000(14)\u0000(15)\u0000(16)\u0000(17)\u0000(18)\u0000(19)\u0000(20)\u00001.2.3.4.5.6.7.8.9.10.11.12.13.14.15.16.17.18.19.20.(a)(b)(c)(d)(e)(f)(g)(h)(i)(j)(k)(l)(m)(n)(o)(p)(q)(r)(s)(t)(u)(v)(w)(x)(y)(z)AGKOUWY母龟一丨丶丿乙亅二亠人儿入八冂冖冫几凵刀力勹匕匚匸十卜卩厂厶又口囗土士夂夊夕大女子宀寸小尢尸屮山巛工己巾干幺广廴廾弋弓彐彡彳心戈戶手支攴文斗斤方无日曰月木欠止歹殳毋比毛氏气水火爪父爻爿片牙牛犬玄玉瓜瓦甘生用田疋疒癶白皮皿目矛矢石示禸禾穴立竹米糸缶网羊羽老而耒耳聿肉臣自至臼舌舛舟艮色艸虍虫血行衣襾見角言谷豆豕豸貝赤走足身車辛辰辵邑酉釆里金長門阜隶隹雨靑非面革韋韭音頁風飛食首香馬骨高髟鬥鬯鬲鬼魚鳥鹵鹿麥麻黃黍黑黹黽鼎鼓鼠鼻齊齒龍龜龠〒卄卅 ゙ ゚ᄀᄁᆪᄂᆬᆭᄃᄄᄅᆰᆱᆲᆳᆴᆵᄚᄆᄇᄈᄡᄉᄊᄋᄌᄍᄎᄏᄐᄑ하ᅢᅣᅤᅥᅦᅧᅨᅩᅪᅫᅬᅭᅮᅯᅰᅱᅲᅳᅴᅵᅠᄔᄕᇇᇈᇌᇎᇓᇗᇙᄜᇝᇟᄝᄞᄠᄢᄣᄧᄩᄫᄬᄭᄮᄯᄲᄶᅀᅇᅌᇱᇲᅗᅘᅙᆄᆅᆈᆑᆒᆔᆞᆡ三四上中下甲丙丁天地(ᄀ)(ᄂ)(ᄃ)(ᄅ)(ᄆ)(ᄇ)(ᄉ)(ᄋ)(ᄌ)(ᄎ)(ᄏ)(ᄐ)(ᄑ)(ᄒ)(가)\u0000(나)\u0000(다)\u0000(라)\u0000(마)\u0000(바)\u0000(사)\u0000(아)\u0000(자)\u0000(차)\u0000(카)\u0000(타)\u0000(파)\u0000(하)\u0000(주)\u0000(一)(二)(三)(四)(五)(六)(七)(八)(九)(十)(月)(火)(水)(木)(金)(土)(日)(株)(有)(社)(名)(特)(財)(祝)(労)(代)(呼)(学)(監)(企)(資)(協)(祭)(休)(自)(至)秘男適優印注項写正左右医宗夜1月2月3月4月5月6月7月8月9月10月11月12月アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヰヱヲアパート\u0000アルファ\u0000アンペア\u0000アールイニング\u0000インチウォンエスクード\u0000エーカー\u0000オンスオームカイリカラット\u0000カロリー\u0000ガロン\u0000ガンマ\u0000ギガ\u0000ギニー\u0000キュリー\u0000ギルダー\u0000キロキログラム\u0000キロメートル\u0000キロワット\u0000グラムトン\u0000クルゼイロ\u0000クローネ\u0000ケースコルナコーポ\u0000サイクル\u0000サンチーム\u0000シリング\u0000センチセントダース\u0000デシドルナノノットハイツパーセント\u0000パーツ\u0000バーレル\u0000ピアストル\u0000ピクル\u0000ピコビルファラッド\u0000フィート\u0000ブッシェル\u0000フランヘクタール\u0000ペソペニヒ\u0000ヘルツペンス\u0000ページ\u0000ベータ\u0000ポイント\u0000ボルト\u0000ホンポンド\u0000ホールホーンマイクロ\u0000マイルマッハマルクマンション\u0000ミクロン\u0000ミリミリバール\u0000メガメガトン\u0000ヤード\u0000ヤールユアンリットル\u0000リラルピー\u0000ルーブル\u0000レムレントゲン\u00000点1点2点3点4点5点6点7点8点9点10点11点12点13点14点15点16点17点18点19点20点21点22点23点24点hPadaAUbaroVpc平成昭和大正明治株式会社\u0000pAnAμAmAkAKBGBcalkcal\u0000pFnFμFμgmgkgHzkHzMHzGHzTHzμlmldlklfmnmμmmmcmkmmm2cm2km2mm3cm3km3m∕sm∕s2\u0000kPaMPaGParadrad∕s\u0000rad∕s2\u0000psμsmspVnVμVmVkVMVpWnWμWmWkWMWkΩMΩa.m.\u0000BqcccdC∕kg\u0000Co.dBGyhaHPinKKKMktlnloglxmbmilmolPHp.m.\u0000PPMPRsrSvWb1日2日3日4日5日6日7日8日9日10日11日12日13日14日15日16日17日18日19日20日21日22日23日24日25日26日27日28日29日30日31日fffiflfflstմնմեմիվնմխעהכלםרתאלٱٻپڀٺٿٹڤڦڄڃچڇڍڌڎڈژڑکگڳڱںڻۀہھےۓڭۆۈۋۅۉېىئائەئوئۇئۆئۈئېئىیئجئحئمئيبجبحبخبمبىبيتجتحتختمتىتيثجثمثىثيجحجمحمخجخحخمسجسحسخسمصحصمضجضحضخضمطحطمظمعجعمغجغمفجفحفخفمفىفيقحقمقىقيكاكجكحكخكلكمكىكيلجلحلخلملىليمجمممىنجنحنخنمنىنيهجهمهىهييحيخيىذٰرٰىٰ ٌّ ٍّ َّ ُّ ِّ ّٰئرئزئنبربزبنترتزتنثرثزثنمانرنزننيريزينئخئهبهتهصخلهنههٰثهسهشمشهـَّـُّـِّطىطيعىعيغىغيسىسيشىشيحىجىخىصىصيضىضيشجشحشخشرسرصرضراًتجمتحجتحمتخمتمجتمحتمخحميحمىسحجسجحسجىسمحسمجسممصححصممشحمشجيشمخشممضحىضخمطمحطممطميعجمعممعمىغممغميغمىفخمقمحقمملحملحيلحىلججلخملمحمحجمحيمجحمخممجخهمجهممنحمنحىنجمنجىنمينمىيممبخيتجيتجىتخيتخىتميتمىجميجحىجمىسخىصحيشحيضحيلجيلمييجييميمميقمينحيعميكمينجحمخيلجمكممجحيحجيمجيفميبحيسخينجيصلےقلےالله\u0000اكبر\u0000محمد\u0000صلعم\u0000رسول\u0000عليه\u0000وسلم\u0000صلىصلى الله عليه وسلم\u0000جل جلاله\u0000—–_{}〔〕【】《》〈〉「」『』,、;:#&*-<>\\$%@ ًـًـّ ْـْءآأؤإةلآلألإ\"'[]^`|~。・ゥャ¢£¬¦¥₩│←↑→↓■○ÀÁÂÃÄÅÇÈÉÊËÌÍÎÏÑÒÓÔÕÖÙÚÛÜÝàáâãäåçèéêëìíîïñòóôõöùúûüýÿĀāĂăĄąĆćĈĉĊċČčĎďĒēĔĕĖėĘęĚěĜĝĞğĠġĢģĤĥĨĩĪīĬĭĮįİĴĵĶķĹĺĻļĽľŃńŅņŇňŌōŎŏŐőŔŕŖŗŘřŚśŜŝŞşŠšŢţŤťŨũŪūŬŭŮůŰűŲųŴŵŶŷŸŹźŻżŽžƠơƯưǍǎǏǐǑǒǓǔǕǖǗǘǙǚǛǜǞǟǠǡǢǣǦǧǨǩǪǫǬǭǮǯǰǴǵǸǹǺǻǼǽǾǿȀȁȂȃȄȅȆȇȈȉȊȋȌȍȎȏȐȑȒȓȔȕȖȗȘșȚțȞȟȦȧȨȩȪȫȬȭȮȯȰȱȲȳ̀́̓̈́ʹ;΅Ά·ΈΉΊΌΎΏΐΪΫάέήίΰϊϋόύώϓϔЀЁЃЇЌЍЎЙйѐёѓїќѝўѶѷӁӂӐӑӒӓӖӗӚӛӜӝӞӟӢӣӤӥӦӧӪӫӬӭӮӯӰӱӲӳӴӵӸӹآأؤإئۀۂۓऩऱऴक़ख़ग़ज़ड़ढ़फ़य़োৌড়ঢ়য়ਲ਼ਸ਼ਖ਼ਗ਼ਜ਼ਫ਼ୈୋୌଡ଼ଢ଼ஔொோௌైೀೇೈೊೋൊോൌේොෝෞགྷཌྷདྷབྷཛྷཀྵཱཱིུྲྀླཱྀྀྒྷྜྷྡྷྦྷྫྷྐྵဦḀḁḂḃḄḅḆḇḈḉḊḋḌḍḎḏḐḑḒḓḔḕḖḗḘḙḚḛḜḝḞḟḠḡḢḣḤḥḦḧḨḩḪḫḬḭḮḯḰḱḲḳḴḵḶḷḸḹḺḻḼḽḾḿṀṁṂṃṄṅṆṇṈṉṊṋṌṍṎṏṐṑṒṓṔṕṖṗṘṙṚṛṜṝṞṟṠṡṢṣṤṥṦṧṨṩṪṫṬṭṮṯṰṱṲṳṴṵṶṷṸṹṺṻṼṽṾṿẀẁẂẃẄẅẆẇẈẉẊẋẌẍẎẏẐẑẒẓẔẕẖẗẘẙẛẠạẢảẤấẦầẨẩẪẫẬậẮắẰằẲẳẴẵẶặẸẹẺẻẼẽẾếỀềỂểỄễỆệỈỉỊịỌọỎỏỐốỒồỔổỖỗỘộỚớỜờỞởỠỡỢợỤụỦủỨứỪừỬửỮữỰựỲỳỴỵỶỷỸỹἀἁἂἃἄἅἆἇἈἉἊἋἌἍἎἏἐἑἒἓἔἕἘἙἚἛἜἝἠἡἢἣἤἥἦἧἨἩἪἫἬἭἮἯἰἱἲἳἴἵἶἷἸἹἺἻἼἽἾἿὀὁὂὃὄὅὈὉὊὋὌὍὐὑὒὓὔὕὖὗὙὛὝὟὠὡὢὣὤὥὦὧὨὩὪὫὬὭὮὯὰάὲέὴήὶίὸόὺύὼώᾀᾁᾂ\u0000ᾃ\u0000ᾄ\u0000ᾅ\u0000ᾆ\u0000ᾇ\u0000ᾈᾉᾊ\u0000ᾋ\u0000ᾌ\u0000ᾍ\u0000ᾎ\u0000ᾏ\u0000ᾐᾑᾒ\u0000ᾓ\u0000ᾔ\u0000ᾕ\u0000ᾖ\u0000ᾗ\u0000ᾘᾙᾚ\u0000ᾛ\u0000ᾜ\u0000ᾝ\u0000ᾞ\u0000ᾟ\u0000ᾠᾡᾢ\u0000ᾣ\u0000ᾤ\u0000ᾥ\u0000ᾦ\u0000ᾧ\u0000ᾨᾩᾪ\u0000ᾫ\u0000ᾬ\u0000ᾭ\u0000ᾮ\u0000ᾯ\u0000ᾰᾱᾲᾳᾴᾶᾷᾸᾹᾺΆᾼι῁ῂῃῄῆῇῈΈῊΉῌ῍῎῏ῐῑῒΐῖῗῘῙῚΊ῝῞῟ῠῡῢΰῤῥῦῧῨῩῪΎῬ῭΅`ῲῳῴῶῷῸΌῺΏῼ´  ΩKÅ↚↛↮⇍⇎⇏∄∉∌∤∦≁≄≇≉≠≢≭≮≯≰≱≴≵≸≹⊀⊁⊄⊅⊈⊉⊬⊭⊮⊯⋠⋡⋢⋣⋪⋫⋬⋭〈〉がぎぐげござじずぜぞだぢづでどばぱびぴぶぷべぺぼぽゔゞガギグゲゴザジズゼゾダヂヅデドバパビピブプベペボポヴヷヸヹヺヾ豈更車賈滑串句龜龜契金喇奈懶癩羅蘿螺裸邏樂洛烙珞落酪駱亂卵欄爛蘭鸞嵐濫藍襤拉臘蠟廊朗浪狼郎來冷勞擄櫓爐盧老蘆虜路露魯鷺碌祿綠菉錄鹿論壟弄籠聾牢磊賂雷壘屢樓淚漏累縷電勒肋凜凌稜綾菱陵讀拏樂諾丹寧怒率異北磻便復不泌數索參塞省葉說殺辰沈拾若掠略亮兩凉梁糧良諒量勵呂女廬旅濾礪閭驪麗黎力曆歷轢年憐戀撚漣煉璉秊練聯輦蓮連鍊列劣咽烈裂說廉念捻殮簾獵令囹寧嶺怜玲瑩羚聆鈴零靈領例禮醴隸惡了僚寮尿料樂燎療蓼遼龍暈阮劉杻柳流溜琉留硫紐類六戮陸倫崙淪輪律慄栗率隆利吏履易李梨泥理痢罹裏裡里離匿溺吝燐璘藺隣鱗麟林淋臨立笠粒狀炙識什茶刺切度拓糖宅洞暴輻行降見廓兀嗀塚晴凞猪益礼神祥福靖精羽蘒諸逸都飯飼館鶴יִײַשׁשׂשּׁשּׂאַאָאּבּגּדּהּוּזּטּיּךּכּלּמּנּסּףּפּצּקּרּשּתּוֹבֿכֿפֿ";
   static final CompactByteArray canonClass = new CompactByteArray("\u0000Ȁꖥ\u0006\u0000\u0080\u0000\u0000Ā\u0000ƀȀʀ̀\u0000\u0000\u0000\u0380ꖥ\u0004ЀҀԀҀҀր\u0600ڀ܀ހࠀꖥ\u000e\u0000\u0880\u0000ऀꖥ\u000f\u0000\u0980ꖥ\u001e\u0000\u0a00\u0a80ꖥƔ\u0000\u0b00ꖥ\u0005\u0000\u0b80\u0000\u0000\u0000", "\u0000\u0c00ꖀ¥ᗦ\ue8a5Ӝ\ue8d8ꔅ\udcca쪥Ӝ쫊ꔋ\udca5ԁꔄ\udca5ࣦ\uf0e6\udcdc\udce6\ue6e6\udcdcꔑê\ueae9ꔠ¥ӦꖊÜꔄ\ue6dc\ue6e6\ue6de\udca5ۦ¥ל\ue6e6\udce6\ue6de\ue4e6\u0a0b\u0c0dฏထሓ\u0014ᔖ\u0017\u0018ᤀ\ue6a5蘀ᬜᴞἠ™\ue6e6\udca5ᨀ⎥攀ꔇ\ue600¥Ӧ\udce6\u0000\ue6e6Ü\ue6e6\udca5⌀⒥Ḁ\ue6dc\ue6e6\udce6\ue6dc\udcdc\ue6dc\udce6\udce6\ue6e6\udce6\udce6\udce6\udce6\ue6a5焀ޥကऀ\u0000\ue6dc\ue6e6ꕧ\u0007ꔐ\tꕿ\tꕿ\tꔇT宥猀থ洀杧থ\u0d00ꔄ殥氀癶ꔎ¥ѺꕌÜ\udca5ᬀ\udc00\udc00\ud8a5㜀膂\u0084ꔅ¥҂\u0000舀\ue6e6ऀ\ue6e6ꔾÜꕰ\u0007\tꖘ\tꕖäꖦæ\ue601ƥӦāǦ\ue6a5Ѐ\ue6a5䠀\udae4\ue8de\ue0e0ꕩ\bࢥ茀᪥脀ꔄ\ue6a5尀");
}
