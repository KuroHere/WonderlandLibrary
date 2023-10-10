package shadersmod.client;

import org.lwjgl.opengl.ARBShaderObjects;

public class ShaderProgramData {
   public int programIDGL;
   public int uniform_texture;
   public int uniform_lightmap;
   public int uniform_normals;
   public int uniform_specular;
   public int uniform_shadow;
   public int uniform_watershadow;
   public int uniform_shadowtex0;
   public int uniform_shadowtex1;
   public int uniform_depthtex0;
   public int uniform_depthtex1;
   public int uniform_shadowcolor;
   public int uniform_shadowcolor0;
   public int uniform_shadowcolor1;
   public int uniform_noisetex;
   public int uniform_gcolor;
   public int uniform_gdepth;
   public int uniform_gnormal;
   public int uniform_composite;
   public int uniform_gaux1;
   public int uniform_gaux2;
   public int uniform_gaux3;
   public int uniform_gaux4;
   public int uniform_colortex0;
   public int uniform_colortex1;
   public int uniform_colortex2;
   public int uniform_colortex3;
   public int uniform_colortex4;
   public int uniform_colortex5;
   public int uniform_colortex6;
   public int uniform_colortex7;
   public int uniform_gdepthtex;
   public int uniform_depthtex2;
   public int uniform_tex;
   public int uniform_heldItemId;
   public int uniform_heldBlockLightValue;
   public int uniform_fogMode;
   public int uniform_fogColor;
   public int uniform_skyColor;
   public int uniform_worldTime;
   public int uniform_moonPhase;
   public int uniform_frameTimeCounter;
   public int uniform_sunAngle;
   public int uniform_shadowAngle;
   public int uniform_rainStrength;
   public int uniform_aspectRatio;
   public int uniform_viewWidth;
   public int uniform_viewHeight;
   public int uniform_near;
   public int uniform_far;
   public int uniform_sunPosition;
   public int uniform_moonPosition;
   public int uniform_upPosition;
   public int uniform_previousCameraPosition;
   public int uniform_cameraPosition;
   public int uniform_gbufferModelView;
   public int uniform_gbufferModelViewInverse;
   public int uniform_gbufferPreviousProjection;
   public int uniform_gbufferProjection;
   public int uniform_gbufferProjectionInverse;
   public int uniform_gbufferPreviousModelView;
   public int uniform_shadowProjection;
   public int uniform_shadowProjectionInverse;
   public int uniform_shadowModelView;
   public int uniform_shadowModelViewInverse;
   public int uniform_wetness;
   public int uniform_eyeAltitude;
   public int uniform_eyeBrightness;
   public int uniform_eyeBrightnessSmooth;
   public int uniform_terrainTextureSize;
   public int uniform_terrainIconSize;
   public int uniform_isEyeInWater;
   public int uniform_hideGUI;
   public int uniform_centerDepthSmooth;
   public int uniform_atlasSize;

   public ShaderProgramData(int var1) {
      this.programIDGL = var1;
      this.uniform_texture = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"texture");
      this.uniform_lightmap = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"lightmap");
      this.uniform_normals = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"normals");
      this.uniform_specular = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"specular");
      this.uniform_shadow = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"shadow");
      this.uniform_watershadow = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"watershadow");
      this.uniform_shadowtex0 = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"shadowtex0");
      this.uniform_shadowtex1 = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"shadowtex1");
      this.uniform_depthtex0 = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"depthtex0");
      this.uniform_depthtex1 = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"depthtex1");
      this.uniform_shadowcolor = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"shadowcolor");
      this.uniform_shadowcolor0 = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"shadowcolor0");
      this.uniform_shadowcolor1 = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"shadowcolor1");
      this.uniform_noisetex = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"noisetex");
      this.uniform_gcolor = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"gcolor");
      this.uniform_gdepth = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"gdepth");
      this.uniform_gnormal = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"gnormal");
      this.uniform_composite = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"composite");
      this.uniform_gaux1 = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"gaux1");
      this.uniform_gaux2 = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"gaux2");
      this.uniform_gaux3 = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"gaux3");
      this.uniform_gaux4 = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"gaux4");
      this.uniform_colortex0 = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"colortex0");
      this.uniform_colortex1 = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"colortex1");
      this.uniform_colortex2 = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"colortex2");
      this.uniform_colortex3 = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"colortex3");
      this.uniform_colortex4 = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"colortex4");
      this.uniform_colortex5 = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"colortex5");
      this.uniform_colortex6 = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"colortex6");
      this.uniform_colortex7 = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"colortex7");
      this.uniform_gdepthtex = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"gdepthtex");
      this.uniform_depthtex2 = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"depthtex2");
      this.uniform_tex = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"tex");
      this.uniform_heldItemId = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"heldItemId");
      this.uniform_heldBlockLightValue = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"heldBlockLightValue");
      this.uniform_fogMode = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"fogMode");
      this.uniform_fogColor = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"fogColor");
      this.uniform_skyColor = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"skyColor");
      this.uniform_worldTime = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"worldTime");
      this.uniform_moonPhase = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"moonPhase");
      this.uniform_frameTimeCounter = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"frameTimeCounter");
      this.uniform_sunAngle = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"sunAngle");
      this.uniform_shadowAngle = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"shadowAngle");
      this.uniform_rainStrength = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"rainStrength");
      this.uniform_aspectRatio = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"aspectRatio");
      this.uniform_viewWidth = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"viewWidth");
      this.uniform_viewHeight = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"viewHeight");
      this.uniform_near = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"near");
      this.uniform_far = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"far");
      this.uniform_sunPosition = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"sunPosition");
      this.uniform_moonPosition = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"moonPosition");
      this.uniform_upPosition = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"upPosition");
      this.uniform_previousCameraPosition = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"previousCameraPosition");
      this.uniform_cameraPosition = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"cameraPosition");
      this.uniform_gbufferModelView = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"gbufferModelView");
      this.uniform_gbufferModelViewInverse = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"gbufferModelViewInverse");
      this.uniform_gbufferPreviousProjection = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"gbufferPreviousProjection");
      this.uniform_gbufferProjection = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"gbufferProjection");
      this.uniform_gbufferProjectionInverse = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"gbufferProjectionInverse");
      this.uniform_gbufferPreviousModelView = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"gbufferPreviousModelView");
      this.uniform_shadowProjection = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"shadowProjection");
      this.uniform_shadowProjectionInverse = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"shadowProjectionInverse");
      this.uniform_shadowModelView = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"shadowModelView");
      this.uniform_shadowModelViewInverse = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"shadowModelViewInverse");
      this.uniform_wetness = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"wetness");
      this.uniform_eyeAltitude = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"eyeAltitude");
      this.uniform_eyeBrightness = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"eyeBrightness");
      this.uniform_eyeBrightnessSmooth = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"eyeBrightnessSmooth");
      this.uniform_terrainTextureSize = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"terrainTextureSize");
      this.uniform_terrainIconSize = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"terrainIconSize");
      this.uniform_isEyeInWater = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"isEyeInWater");
      this.uniform_hideGUI = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"hideGUI");
      this.uniform_centerDepthSmooth = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"centerDepthSmooth");
      this.uniform_atlasSize = ARBShaderObjects.glGetUniformLocationARB(var1, (CharSequence)"atlasSize");
   }
}
