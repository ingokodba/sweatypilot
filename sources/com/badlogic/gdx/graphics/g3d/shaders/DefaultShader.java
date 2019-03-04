package com.badlogic.gdx.graphics.g3d.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.Attributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.CubemapAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.DepthTestAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.AmbientCubemap;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.shaders.BaseShader.GlobalSetter;
import com.badlogic.gdx.graphics.g3d.shaders.BaseShader.LocalSetter;
import com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter;
import com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Uniform;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.Iterator;

public class DefaultShader extends BaseShader {
    @Deprecated
    public static int defaultCullFace = GL20.GL_BACK;
    @Deprecated
    public static int defaultDepthFunc = GL20.GL_LEQUAL;
    private static String defaultFragmentShader = null;
    private static String defaultVertexShader = null;
    protected static long implementedFlags = ((((BlendingAttribute.Type | TextureAttribute.Diffuse) | ColorAttribute.Diffuse) | ColorAttribute.Specular) | FloatAttribute.Shininess);
    private static final long optionalAttributes = (IntAttribute.CullFace | DepthTestAttribute.Type);
    private static final Attributes tmpAttributes = new Attributes();
    protected final AmbientCubemap ambientCubemap;
    protected final long attributesMask;
    private Camera camera;
    protected final Config config;
    protected int dirLightsColorOffset;
    protected int dirLightsDirectionOffset;
    protected int dirLightsLoc;
    protected int dirLightsSize;
    protected final DirectionalLight[] directionalLights;
    protected final boolean environmentCubemap;
    protected final boolean lighting;
    private boolean lightsSet;
    private Matrix3 normalMatrix;
    protected final PointLight[] pointLights;
    protected int pointLightsColorOffset;
    protected int pointLightsIntensityOffset;
    protected int pointLightsLoc;
    protected int pointLightsPositionOffset;
    protected int pointLightsSize;
    private Renderable renderable;
    protected final boolean shadowMap;
    private float time;
    private final Vector3 tmpV1;
    public final int u_alphaTest;
    protected final int u_ambientCubemap;
    public final int u_ambientTexture;
    public final int u_ambientUVTransform;
    public final int u_bones;
    public final int u_cameraDirection;
    public final int u_cameraPosition;
    public final int u_cameraUp;
    public final int u_diffuseColor;
    public final int u_diffuseTexture;
    public final int u_diffuseUVTransform;
    protected final int u_dirLights0color;
    protected final int u_dirLights0direction;
    protected final int u_dirLights1color;
    public final int u_emissiveColor;
    public final int u_emissiveTexture;
    public final int u_emissiveUVTransform;
    protected final int u_environmentCubemap;
    protected final int u_fogColor;
    public final int u_normalMatrix;
    public final int u_normalTexture;
    public final int u_normalUVTransform;
    public final int u_opacity;
    protected final int u_pointLights0color;
    protected final int u_pointLights0intensity;
    protected final int u_pointLights0position;
    protected final int u_pointLights1color;
    public final int u_projTrans;
    public final int u_projViewTrans;
    public final int u_projViewWorldTrans;
    public final int u_reflectionColor;
    public final int u_reflectionTexture;
    public final int u_reflectionUVTransform;
    protected final int u_shadowMapProjViewTrans;
    protected final int u_shadowPCFOffset;
    protected final int u_shadowTexture;
    public final int u_shininess;
    public final int u_specularColor;
    public final int u_specularTexture;
    public final int u_specularUVTransform;
    public final int u_time;
    public final int u_viewTrans;
    public final int u_viewWorldTrans;
    public final int u_worldTrans;
    private long vertexMask;

    public static class Config {
        public int defaultCullFace = -1;
        public int defaultDepthFunc = -1;
        public String fragmentShader = null;
        public boolean ignoreUnimplemented = true;
        public int numBones = 12;
        public int numDirectionalLights = 2;
        public int numPointLights = 5;
        public int numSpotLights = 0;
        public String vertexShader = null;

        public Config(String vertexShader, String fragmentShader) {
            this.vertexShader = vertexShader;
            this.fragmentShader = fragmentShader;
        }
    }

    public static class Inputs {
        public static final Uniform alphaTest = new Uniform("u_alphaTest");
        public static final Uniform ambientCube = new Uniform("u_ambientCubemap");
        public static final Uniform ambientTexture = new Uniform("u_ambientTexture", TextureAttribute.Ambient);
        public static final Uniform ambientUVTransform = new Uniform("u_ambientUVTransform", TextureAttribute.Ambient);
        public static final Uniform bones = new Uniform("u_bones");
        public static final Uniform cameraDirection = new Uniform("u_cameraDirection");
        public static final Uniform cameraPosition = new Uniform("u_cameraPosition");
        public static final Uniform cameraUp = new Uniform("u_cameraUp");
        public static final Uniform diffuseColor = new Uniform("u_diffuseColor", ColorAttribute.Diffuse);
        public static final Uniform diffuseTexture = new Uniform("u_diffuseTexture", TextureAttribute.Diffuse);
        public static final Uniform diffuseUVTransform = new Uniform("u_diffuseUVTransform", TextureAttribute.Diffuse);
        public static final Uniform dirLights = new Uniform("u_dirLights");
        public static final Uniform emissiveColor = new Uniform("u_emissiveColor", ColorAttribute.Emissive);
        public static final Uniform emissiveTexture = new Uniform("u_emissiveTexture", TextureAttribute.Emissive);
        public static final Uniform emissiveUVTransform = new Uniform("u_emissiveUVTransform", TextureAttribute.Emissive);
        public static final Uniform environmentCubemap = new Uniform("u_environmentCubemap");
        public static final Uniform normalMatrix = new Uniform("u_normalMatrix");
        public static final Uniform normalTexture = new Uniform("u_normalTexture", TextureAttribute.Normal);
        public static final Uniform normalUVTransform = new Uniform("u_normalUVTransform", TextureAttribute.Normal);
        public static final Uniform opacity = new Uniform("u_opacity", BlendingAttribute.Type);
        public static final Uniform pointLights = new Uniform("u_pointLights");
        public static final Uniform projTrans = new Uniform("u_projTrans");
        public static final Uniform projViewTrans = new Uniform("u_projViewTrans");
        public static final Uniform projViewWorldTrans = new Uniform("u_projViewWorldTrans");
        public static final Uniform reflectionColor = new Uniform("u_reflectionColor", ColorAttribute.Reflection);
        public static final Uniform reflectionTexture = new Uniform("u_reflectionTexture", TextureAttribute.Reflection);
        public static final Uniform reflectionUVTransform = new Uniform("u_reflectionUVTransform", TextureAttribute.Reflection);
        public static final Uniform shininess = new Uniform("u_shininess", FloatAttribute.Shininess);
        public static final Uniform specularColor = new Uniform("u_specularColor", ColorAttribute.Specular);
        public static final Uniform specularTexture = new Uniform("u_specularTexture", TextureAttribute.Specular);
        public static final Uniform specularUVTransform = new Uniform("u_specularUVTransform", TextureAttribute.Specular);
        public static final Uniform viewTrans = new Uniform("u_viewTrans");
        public static final Uniform viewWorldTrans = new Uniform("u_viewWorldTrans");
        public static final Uniform worldTrans = new Uniform("u_worldTrans");
    }

    public static class Setters {
        public static final Setter ambientTexture = new LocalSetter() {
            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                shader.set(inputID, shader.context.textureBinder.bind(((TextureAttribute) combinedAttributes.get(TextureAttribute.Ambient)).textureDescription));
            }
        };
        public static final Setter ambientUVTransform = new LocalSetter() {
            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                TextureAttribute ta = (TextureAttribute) combinedAttributes.get(TextureAttribute.Ambient);
                shader.set(inputID, ta.offsetU, ta.offsetV, ta.scaleU, ta.scaleV);
            }
        };
        public static final Setter cameraDirection = new C00995();
        public static final Setter cameraPosition = new C00984();
        public static final Setter cameraUp = new C01006();
        public static final Setter diffuseColor = new LocalSetter() {
            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                shader.set(inputID, ((ColorAttribute) combinedAttributes.get(ColorAttribute.Diffuse)).color);
            }
        };
        public static final Setter diffuseTexture = new LocalSetter() {
            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                shader.set(inputID, shader.context.textureBinder.bind(((TextureAttribute) combinedAttributes.get(TextureAttribute.Diffuse)).textureDescription));
            }
        };
        public static final Setter diffuseUVTransform = new LocalSetter() {
            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                TextureAttribute ta = (TextureAttribute) combinedAttributes.get(TextureAttribute.Diffuse);
                shader.set(inputID, ta.offsetU, ta.offsetV, ta.scaleU, ta.scaleV);
            }
        };
        public static final Setter emissiveColor = new LocalSetter() {
            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                shader.set(inputID, ((ColorAttribute) combinedAttributes.get(ColorAttribute.Emissive)).color);
            }
        };
        public static final Setter emissiveTexture = new LocalSetter() {
            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                shader.set(inputID, shader.context.textureBinder.bind(((TextureAttribute) combinedAttributes.get(TextureAttribute.Emissive)).textureDescription));
            }
        };
        public static final Setter emissiveUVTransform = new LocalSetter() {
            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                TextureAttribute ta = (TextureAttribute) combinedAttributes.get(TextureAttribute.Emissive);
                shader.set(inputID, ta.offsetU, ta.offsetV, ta.scaleU, ta.scaleV);
            }
        };
        public static final Setter environmentCubemap = new LocalSetter() {
            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                if (combinedAttributes.has(CubemapAttribute.EnvironmentMap)) {
                    shader.set(inputID, shader.context.textureBinder.bind(((CubemapAttribute) combinedAttributes.get(CubemapAttribute.EnvironmentMap)).textureDescription));
                }
            }
        };
        public static final Setter normalMatrix = new LocalSetter() {
            private final Matrix3 tmpM = new Matrix3();

            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                shader.set(inputID, this.tmpM.set(renderable.worldTransform).inv().transpose());
            }
        };
        public static final Setter normalTexture = new LocalSetter() {
            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                shader.set(inputID, shader.context.textureBinder.bind(((TextureAttribute) combinedAttributes.get(TextureAttribute.Normal)).textureDescription));
            }
        };
        public static final Setter normalUVTransform = new LocalSetter() {
            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                TextureAttribute ta = (TextureAttribute) combinedAttributes.get(TextureAttribute.Normal);
                shader.set(inputID, ta.offsetU, ta.offsetV, ta.scaleU, ta.scaleV);
            }
        };
        public static final Setter projTrans = new C00951();
        public static final Setter projViewTrans = new C00973();
        public static final Setter projViewWorldTrans = new C01039();
        public static final Setter reflectionColor = new LocalSetter() {
            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                shader.set(inputID, ((ColorAttribute) combinedAttributes.get(ColorAttribute.Reflection)).color);
            }
        };
        public static final Setter reflectionTexture = new LocalSetter() {
            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                shader.set(inputID, shader.context.textureBinder.bind(((TextureAttribute) combinedAttributes.get(TextureAttribute.Reflection)).textureDescription));
            }
        };
        public static final Setter reflectionUVTransform = new LocalSetter() {
            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                TextureAttribute ta = (TextureAttribute) combinedAttributes.get(TextureAttribute.Reflection);
                shader.set(inputID, ta.offsetU, ta.offsetV, ta.scaleU, ta.scaleV);
            }
        };
        public static final Setter shininess = new LocalSetter() {
            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                shader.set(inputID, ((FloatAttribute) combinedAttributes.get(FloatAttribute.Shininess)).value);
            }
        };
        public static final Setter specularColor = new LocalSetter() {
            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                shader.set(inputID, ((ColorAttribute) combinedAttributes.get(ColorAttribute.Specular)).color);
            }
        };
        public static final Setter specularTexture = new LocalSetter() {
            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                shader.set(inputID, shader.context.textureBinder.bind(((TextureAttribute) combinedAttributes.get(TextureAttribute.Specular)).textureDescription));
            }
        };
        public static final Setter specularUVTransform = new LocalSetter() {
            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                TextureAttribute ta = (TextureAttribute) combinedAttributes.get(TextureAttribute.Specular);
                shader.set(inputID, ta.offsetU, ta.offsetV, ta.scaleU, ta.scaleV);
            }
        };
        public static final Setter viewTrans = new C00962();
        public static final Setter viewWorldTrans = new C01028();
        public static final Setter worldTrans = new C01017();

        /* renamed from: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader$Setters$1 */
        static class C00951 extends GlobalSetter {
            C00951() {
            }

            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                shader.set(inputID, shader.camera.projection);
            }
        }

        /* renamed from: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader$Setters$2 */
        static class C00962 extends GlobalSetter {
            C00962() {
            }

            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                shader.set(inputID, shader.camera.view);
            }
        }

        /* renamed from: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader$Setters$3 */
        static class C00973 extends GlobalSetter {
            C00973() {
            }

            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                shader.set(inputID, shader.camera.combined);
            }
        }

        /* renamed from: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader$Setters$4 */
        static class C00984 extends GlobalSetter {
            C00984() {
            }

            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                shader.set(inputID, shader.camera.position.f107x, shader.camera.position.f108y, shader.camera.position.f109z, 1.1881f / (shader.camera.far * shader.camera.far));
            }
        }

        /* renamed from: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader$Setters$5 */
        static class C00995 extends GlobalSetter {
            C00995() {
            }

            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                shader.set(inputID, shader.camera.direction);
            }
        }

        /* renamed from: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader$Setters$6 */
        static class C01006 extends GlobalSetter {
            C01006() {
            }

            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                shader.set(inputID, shader.camera.up);
            }
        }

        /* renamed from: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader$Setters$7 */
        static class C01017 extends LocalSetter {
            C01017() {
            }

            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                shader.set(inputID, renderable.worldTransform);
            }
        }

        /* renamed from: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader$Setters$8 */
        static class C01028 extends LocalSetter {
            final Matrix4 temp = new Matrix4();

            C01028() {
            }

            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                shader.set(inputID, this.temp.set(shader.camera.view).mul(renderable.worldTransform));
            }
        }

        /* renamed from: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader$Setters$9 */
        static class C01039 extends LocalSetter {
            final Matrix4 temp = new Matrix4();

            C01039() {
            }

            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                shader.set(inputID, this.temp.set(shader.camera.combined).mul(renderable.worldTransform));
            }
        }

        public static class ACubemap extends LocalSetter {
            private static final float[] ones = new float[]{1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f};
            private static final Vector3 tmpV1 = new Vector3();
            private final AmbientCubemap cacheAmbientCubemap = new AmbientCubemap();
            public final int dirLightsOffset;
            public final int pointLightsOffset;

            public ACubemap(int dirLightsOffset, int pointLightsOffset) {
                this.dirLightsOffset = dirLightsOffset;
                this.pointLightsOffset = pointLightsOffset;
            }

            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                if (renderable.environment == null) {
                    shader.program.setUniform3fv(shader.loc(inputID), ones, 0, ones.length);
                    return;
                }
                int i;
                renderable.worldTransform.getTranslation(tmpV1);
                if (combinedAttributes.has(ColorAttribute.AmbientLight)) {
                    this.cacheAmbientCubemap.set(((ColorAttribute) combinedAttributes.get(ColorAttribute.AmbientLight)).color);
                }
                for (i = this.dirLightsOffset; i < renderable.environment.directionalLights.size; i++) {
                    this.cacheAmbientCubemap.add(((DirectionalLight) renderable.environment.directionalLights.get(i)).color, ((DirectionalLight) renderable.environment.directionalLights.get(i)).direction);
                }
                for (i = this.pointLightsOffset; i < renderable.environment.pointLights.size; i++) {
                    this.cacheAmbientCubemap.add(((PointLight) renderable.environment.pointLights.get(i)).color, ((PointLight) renderable.environment.pointLights.get(i)).position, tmpV1, ((PointLight) renderable.environment.pointLights.get(i)).intensity);
                }
                this.cacheAmbientCubemap.clamp();
                shader.program.setUniform3fv(shader.loc(inputID), this.cacheAmbientCubemap.data, 0, this.cacheAmbientCubemap.data.length);
            }
        }

        public static class Bones extends LocalSetter {
            private static final Matrix4 idtMatrix = new Matrix4();
            public final float[] bones;

            public Bones(int numBones) {
                this.bones = new float[(numBones * 16)];
            }

            public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                for (int i = 0; i < this.bones.length; i++) {
                    int idx = i / 16;
                    float[] fArr = this.bones;
                    float f = (renderable.bones == null || idx >= renderable.bones.length || renderable.bones[idx] == null) ? idtMatrix.val[i % 16] : renderable.bones[idx].val[i % 16];
                    fArr[i] = f;
                }
                shader.program.setUniformMatrix4fv(shader.loc(inputID), this.bones, 0, this.bones.length);
            }
        }
    }

    public static String getDefaultVertexShader() {
        if (defaultVertexShader == null) {
            defaultVertexShader = Gdx.files.classpath("com/badlogic/gdx/graphics/g3d/shaders/default.vertex.glsl").readString();
        }
        return defaultVertexShader;
    }

    public static String getDefaultFragmentShader() {
        if (defaultFragmentShader == null) {
            defaultFragmentShader = Gdx.files.classpath("com/badlogic/gdx/graphics/g3d/shaders/default.fragment.glsl").readString();
        }
        return defaultFragmentShader;
    }

    public DefaultShader(Renderable renderable) {
        this(renderable, new Config());
    }

    public DefaultShader(Renderable renderable, Config config) {
        this(renderable, config, createPrefix(renderable, config));
    }

    public DefaultShader(Renderable renderable, Config config, String prefix) {
        this(renderable, config, prefix, config.vertexShader != null ? config.vertexShader : getDefaultVertexShader(), config.fragmentShader != null ? config.fragmentShader : getDefaultFragmentShader());
    }

    public DefaultShader(Renderable renderable, Config config, String prefix, String vertexShader, String fragmentShader) {
        this(renderable, config, new ShaderProgram(prefix + vertexShader, prefix + fragmentShader));
    }

    public DefaultShader(Renderable renderable, Config config, ShaderProgram shaderProgram) {
        boolean z;
        int i;
        int i2;
        boolean z2 = true;
        int i3 = -1;
        int i4 = 0;
        this.u_dirLights0color = register(new Uniform("u_dirLights[0].color"));
        this.u_dirLights0direction = register(new Uniform("u_dirLights[0].direction"));
        this.u_dirLights1color = register(new Uniform("u_dirLights[1].color"));
        this.u_pointLights0color = register(new Uniform("u_pointLights[0].color"));
        this.u_pointLights0position = register(new Uniform("u_pointLights[0].position"));
        this.u_pointLights0intensity = register(new Uniform("u_pointLights[0].intensity"));
        this.u_pointLights1color = register(new Uniform("u_pointLights[1].color"));
        this.u_fogColor = register(new Uniform("u_fogColor"));
        this.u_shadowMapProjViewTrans = register(new Uniform("u_shadowMapProjViewTrans"));
        this.u_shadowTexture = register(new Uniform("u_shadowTexture"));
        this.u_shadowPCFOffset = register(new Uniform("u_shadowPCFOffset"));
        this.ambientCubemap = new AmbientCubemap();
        this.normalMatrix = new Matrix3();
        this.tmpV1 = new Vector3();
        Attributes attributes = combineAttributes(renderable);
        this.config = config;
        this.program = shaderProgram;
        if (renderable.environment != null) {
            z = true;
        } else {
            z = false;
        }
        this.lighting = z;
        if (attributes.has(CubemapAttribute.EnvironmentMap) || (this.lighting && attributes.has(CubemapAttribute.EnvironmentMap))) {
            z = true;
        } else {
            z = false;
        }
        this.environmentCubemap = z;
        if (!this.lighting || renderable.environment.shadowMap == null) {
            z2 = false;
        }
        this.shadowMap = z2;
        this.renderable = renderable;
        this.attributesMask = attributes.getMask() | optionalAttributes;
        this.vertexMask = renderable.mesh.getVertexAttributes().getMask();
        if (!this.lighting || config.numDirectionalLights <= 0) {
            i = 0;
        } else {
            i = config.numDirectionalLights;
        }
        this.directionalLights = new DirectionalLight[i];
        for (i2 = 0; i2 < this.directionalLights.length; i2++) {
            this.directionalLights[i2] = new DirectionalLight();
        }
        if (this.lighting && config.numPointLights > 0) {
            i4 = config.numPointLights;
        }
        this.pointLights = new PointLight[i4];
        for (i2 = 0; i2 < this.pointLights.length; i2++) {
            this.pointLights[i2] = new PointLight();
        }
        if (config.ignoreUnimplemented || (implementedFlags & this.attributesMask) == this.attributesMask) {
            this.u_projTrans = register(Inputs.projTrans, Setters.projTrans);
            this.u_viewTrans = register(Inputs.viewTrans, Setters.viewTrans);
            this.u_projViewTrans = register(Inputs.projViewTrans, Setters.projViewTrans);
            this.u_cameraPosition = register(Inputs.cameraPosition, Setters.cameraPosition);
            this.u_cameraDirection = register(Inputs.cameraDirection, Setters.cameraDirection);
            this.u_cameraUp = register(Inputs.cameraUp, Setters.cameraUp);
            this.u_time = register(new Uniform("u_time"));
            this.u_worldTrans = register(Inputs.worldTrans, Setters.worldTrans);
            this.u_viewWorldTrans = register(Inputs.viewWorldTrans, Setters.viewWorldTrans);
            this.u_projViewWorldTrans = register(Inputs.projViewWorldTrans, Setters.projViewWorldTrans);
            this.u_normalMatrix = register(Inputs.normalMatrix, Setters.normalMatrix);
            i = (renderable.bones == null || config.numBones <= 0) ? -1 : register(Inputs.bones, new Bones(config.numBones));
            this.u_bones = i;
            this.u_shininess = register(Inputs.shininess, Setters.shininess);
            this.u_opacity = register(Inputs.opacity);
            this.u_diffuseColor = register(Inputs.diffuseColor, Setters.diffuseColor);
            this.u_diffuseTexture = register(Inputs.diffuseTexture, Setters.diffuseTexture);
            this.u_diffuseUVTransform = register(Inputs.diffuseUVTransform, Setters.diffuseUVTransform);
            this.u_specularColor = register(Inputs.specularColor, Setters.specularColor);
            this.u_specularTexture = register(Inputs.specularTexture, Setters.specularTexture);
            this.u_specularUVTransform = register(Inputs.specularUVTransform, Setters.specularUVTransform);
            this.u_emissiveColor = register(Inputs.emissiveColor, Setters.emissiveColor);
            this.u_emissiveTexture = register(Inputs.emissiveTexture, Setters.emissiveTexture);
            this.u_emissiveUVTransform = register(Inputs.emissiveUVTransform, Setters.emissiveUVTransform);
            this.u_reflectionColor = register(Inputs.reflectionColor, Setters.reflectionColor);
            this.u_reflectionTexture = register(Inputs.reflectionTexture, Setters.reflectionTexture);
            this.u_reflectionUVTransform = register(Inputs.reflectionUVTransform, Setters.reflectionUVTransform);
            this.u_normalTexture = register(Inputs.normalTexture, Setters.normalTexture);
            this.u_normalUVTransform = register(Inputs.normalUVTransform, Setters.normalUVTransform);
            this.u_ambientTexture = register(Inputs.ambientTexture, Setters.ambientTexture);
            this.u_ambientUVTransform = register(Inputs.ambientUVTransform, Setters.ambientUVTransform);
            this.u_alphaTest = register(Inputs.alphaTest);
            this.u_ambientCubemap = this.lighting ? register(Inputs.ambientCube, new ACubemap(config.numDirectionalLights, config.numPointLights)) : -1;
            if (this.environmentCubemap) {
                i3 = register(Inputs.environmentCubemap, Setters.environmentCubemap);
            }
            this.u_environmentCubemap = i3;
            return;
        }
        throw new GdxRuntimeException("Some attributes not implemented yet (" + this.attributesMask + ")");
    }

    public void init() {
        ShaderProgram program = this.program;
        this.program = null;
        init(program, this.renderable);
        this.renderable = null;
        this.dirLightsLoc = loc(this.u_dirLights0color);
        this.dirLightsColorOffset = loc(this.u_dirLights0color) - this.dirLightsLoc;
        this.dirLightsDirectionOffset = loc(this.u_dirLights0direction) - this.dirLightsLoc;
        this.dirLightsSize = loc(this.u_dirLights1color) - this.dirLightsLoc;
        if (this.dirLightsSize < 0) {
            this.dirLightsSize = 0;
        }
        this.pointLightsLoc = loc(this.u_pointLights0color);
        this.pointLightsColorOffset = loc(this.u_pointLights0color) - this.pointLightsLoc;
        this.pointLightsPositionOffset = loc(this.u_pointLights0position) - this.pointLightsLoc;
        this.pointLightsIntensityOffset = has(this.u_pointLights0intensity) ? loc(this.u_pointLights0intensity) - this.pointLightsLoc : -1;
        this.pointLightsSize = loc(this.u_pointLights1color) - this.pointLightsLoc;
        if (this.pointLightsSize < 0) {
            this.pointLightsSize = 0;
        }
    }

    private static final boolean and(long mask, long flag) {
        return (mask & flag) == flag;
    }

    private static final boolean or(long mask, long flag) {
        return (mask & flag) != 0;
    }

    private static final Attributes combineAttributes(Renderable renderable) {
        tmpAttributes.clear();
        if (renderable.environment != null) {
            tmpAttributes.set(renderable.environment);
        }
        if (renderable.material != null) {
            tmpAttributes.set(renderable.material);
        }
        return tmpAttributes;
    }

    public static String createPrefix(Renderable renderable, Config config) {
        Attributes attributes = combineAttributes(renderable);
        String prefix = "";
        long attributesMask = attributes.getMask();
        long vertexMask = renderable.mesh.getVertexAttributes().getMask();
        if (and(vertexMask, 1)) {
            prefix = prefix + "#define positionFlag\n";
        }
        if (or(vertexMask, 6)) {
            prefix = prefix + "#define colorFlag\n";
        }
        if (and(vertexMask, 256)) {
            prefix = prefix + "#define binormalFlag\n";
        }
        if (and(vertexMask, 128)) {
            prefix = prefix + "#define tangentFlag\n";
        }
        if (and(vertexMask, 8)) {
            prefix = prefix + "#define normalFlag\n";
        }
        if ((and(vertexMask, 8) || and(vertexMask, 384)) && renderable.environment != null) {
            prefix = (((prefix + "#define lightingFlag\n") + "#define ambientCubemapFlag\n") + "#define numDirectionalLights " + config.numDirectionalLights + "\n") + "#define numPointLights " + config.numPointLights + "\n";
            if (attributes.has(ColorAttribute.Fog)) {
                prefix = prefix + "#define fogFlag\n";
            }
            if (renderable.environment.shadowMap != null) {
                prefix = prefix + "#define shadowMapFlag\n";
            }
            if (attributes.has(CubemapAttribute.EnvironmentMap) || attributes.has(CubemapAttribute.EnvironmentMap)) {
                prefix = prefix + "#define environmentCubemapFlag\n";
            }
        }
        int n = renderable.mesh.getVertexAttributes().size();
        for (int i = 0; i < n; i++) {
            VertexAttribute attr = renderable.mesh.getVertexAttributes().get(i);
            if (attr.usage == 64) {
                prefix = prefix + "#define boneWeight" + attr.unit + "Flag\n";
            } else if (attr.usage == 16) {
                prefix = prefix + "#define texCoord" + attr.unit + "Flag\n";
            }
        }
        if ((BlendingAttribute.Type & attributesMask) == BlendingAttribute.Type) {
            prefix = prefix + "#define blendedFlag\n";
        }
        if ((TextureAttribute.Diffuse & attributesMask) == TextureAttribute.Diffuse) {
            prefix = (prefix + "#define diffuseTextureFlag\n") + "#define diffuseTextureCoord texCoord0\n";
        }
        if ((TextureAttribute.Specular & attributesMask) == TextureAttribute.Specular) {
            prefix = (prefix + "#define specularTextureFlag\n") + "#define specularTextureCoord texCoord0\n";
        }
        if ((TextureAttribute.Normal & attributesMask) == TextureAttribute.Normal) {
            prefix = (prefix + "#define normalTextureFlag\n") + "#define normalTextureCoord texCoord0\n";
        }
        if ((TextureAttribute.Emissive & attributesMask) == TextureAttribute.Emissive) {
            prefix = (prefix + "#define emissiveTextureFlag\n") + "#define emissiveTextureCoord texCoord0\n";
        }
        if ((TextureAttribute.Reflection & attributesMask) == TextureAttribute.Reflection) {
            prefix = (prefix + "#define reflectionTextureFlag\n") + "#define reflectionTextureCoord texCoord0\n";
        }
        if ((TextureAttribute.Ambient & attributesMask) == TextureAttribute.Ambient) {
            prefix = (prefix + "#define ambientTextureFlag\n") + "#define ambientTextureCoord texCoord0\n";
        }
        if ((ColorAttribute.Diffuse & attributesMask) == ColorAttribute.Diffuse) {
            prefix = prefix + "#define diffuseColorFlag\n";
        }
        if ((ColorAttribute.Specular & attributesMask) == ColorAttribute.Specular) {
            prefix = prefix + "#define specularColorFlag\n";
        }
        if ((ColorAttribute.Emissive & attributesMask) == ColorAttribute.Emissive) {
            prefix = prefix + "#define emissiveColorFlag\n";
        }
        if ((ColorAttribute.Reflection & attributesMask) == ColorAttribute.Reflection) {
            prefix = prefix + "#define reflectionColorFlag\n";
        }
        if ((FloatAttribute.Shininess & attributesMask) == FloatAttribute.Shininess) {
            prefix = prefix + "#define shininessFlag\n";
        }
        if ((FloatAttribute.AlphaTest & attributesMask) == FloatAttribute.AlphaTest) {
            prefix = prefix + "#define alphaTestFlag\n";
        }
        if (renderable.bones == null || config.numBones <= 0) {
            return prefix;
        }
        return prefix + "#define numBones " + config.numBones + "\n";
    }

    public boolean canRender(Renderable renderable) {
        if (this.attributesMask == (combineAttributes(renderable).getMask() | optionalAttributes) && this.vertexMask == renderable.mesh.getVertexAttributes().getMask()) {
            if ((renderable.environment != null) == this.lighting) {
                return true;
            }
        }
        return false;
    }

    public int compareTo(Shader other) {
        if (other == null) {
            return -1;
        }
        return other == this ? 0 : 0;
    }

    public boolean equals(Object obj) {
        return obj instanceof DefaultShader ? equals((DefaultShader) obj) : false;
    }

    public boolean equals(DefaultShader obj) {
        return obj == this;
    }

    public void begin(Camera camera, RenderContext context) {
        super.begin(camera, context);
        for (DirectionalLight dirLight : this.directionalLights) {
            dirLight.set(0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f);
        }
        for (PointLight pointLight : this.pointLights) {
            pointLight.set(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        }
        this.lightsSet = false;
        if (has(this.u_time)) {
            int i = this.u_time;
            float deltaTime = this.time + Gdx.graphics.getDeltaTime();
            this.time = deltaTime;
            set(i, deltaTime);
        }
    }

    public void render(Renderable renderable, Attributes combinedAttributes) {
        if (!combinedAttributes.has(BlendingAttribute.Type)) {
            this.context.setBlending(false, GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        }
        bindMaterial(combinedAttributes);
        if (this.lighting) {
            bindLights(renderable, combinedAttributes);
        }
        super.render(renderable, combinedAttributes);
    }

    public void end() {
        super.end();
    }

    protected void bindMaterial(Attributes attributes) {
        int cullFace = this.config.defaultCullFace == -1 ? defaultCullFace : this.config.defaultCullFace;
        int depthFunc = this.config.defaultDepthFunc == -1 ? defaultDepthFunc : this.config.defaultDepthFunc;
        float depthRangeNear = 0.0f;
        float depthRangeFar = 1.0f;
        boolean depthMask = true;
        Iterator i$ = attributes.iterator();
        while (i$.hasNext()) {
            Attribute attr = (Attribute) i$.next();
            long t = attr.type;
            if (BlendingAttribute.is(t)) {
                this.context.setBlending(true, ((BlendingAttribute) attr).sourceFunction, ((BlendingAttribute) attr).destFunction);
                set(this.u_opacity, ((BlendingAttribute) attr).opacity);
            } else if ((IntAttribute.CullFace & t) == IntAttribute.CullFace) {
                cullFace = ((IntAttribute) attr).value;
            } else if ((FloatAttribute.AlphaTest & t) == FloatAttribute.AlphaTest) {
                set(this.u_alphaTest, ((FloatAttribute) attr).value);
            } else if ((DepthTestAttribute.Type & t) == DepthTestAttribute.Type) {
                DepthTestAttribute dta = (DepthTestAttribute) attr;
                depthFunc = dta.depthFunc;
                depthRangeNear = dta.depthRangeNear;
                depthRangeFar = dta.depthRangeFar;
                depthMask = dta.depthMask;
            } else if (!this.config.ignoreUnimplemented) {
                throw new GdxRuntimeException("Unknown material attribute: " + attr.toString());
            }
        }
        this.context.setCullFace(cullFace);
        this.context.setDepthTest(depthFunc, depthRangeNear, depthRangeFar);
        this.context.setDepthMask(depthMask);
    }

    protected void bindLights(Renderable renderable, Attributes attributes) {
        int i;
        int idx;
        Environment lights = renderable.environment;
        Array<DirectionalLight> dirs = lights.directionalLights;
        Array<PointLight> points = lights.pointLights;
        if (this.dirLightsLoc >= 0) {
            i = 0;
            while (i < this.directionalLights.length) {
                if (dirs == null || i >= dirs.size) {
                    if (!this.lightsSet || this.directionalLights[i].color.f39r != 0.0f || this.directionalLights[i].color.f38g != 0.0f || this.directionalLights[i].color.f37b != 0.0f) {
                        this.directionalLights[i].color.set(0.0f, 0.0f, 0.0f, 1.0f);
                        idx = this.dirLightsLoc + (this.dirLightsSize * i);
                        this.program.setUniformf(this.dirLightsColorOffset + idx, this.directionalLights[i].color.f39r, this.directionalLights[i].color.f38g, this.directionalLights[i].color.f37b);
                        this.program.setUniformf(this.dirLightsDirectionOffset + idx, this.directionalLights[i].direction);
                        if (this.dirLightsSize <= 0) {
                            break;
                        }
                    }
                } else if (!this.lightsSet || !this.directionalLights[i].equals((DirectionalLight) dirs.get(i))) {
                    this.directionalLights[i].set((DirectionalLight) dirs.get(i));
                    idx = this.dirLightsLoc + (this.dirLightsSize * i);
                    this.program.setUniformf(this.dirLightsColorOffset + idx, this.directionalLights[i].color.f39r, this.directionalLights[i].color.f38g, this.directionalLights[i].color.f37b);
                    this.program.setUniformf(this.dirLightsDirectionOffset + idx, this.directionalLights[i].direction);
                    if (this.dirLightsSize <= 0) {
                        break;
                    }
                }
                i++;
            }
        }
        if (this.pointLightsLoc >= 0) {
            i = 0;
            while (i < this.pointLights.length) {
                if (points == null || i >= points.size) {
                    if (!this.lightsSet || this.pointLights[i].intensity != 0.0f) {
                        this.pointLights[i].intensity = 0.0f;
                        idx = this.pointLightsLoc + (this.pointLightsSize * i);
                        this.program.setUniformf(this.pointLightsColorOffset + idx, this.pointLights[i].color.f39r * this.pointLights[i].intensity, this.pointLights[i].color.f38g * this.pointLights[i].intensity, this.pointLights[i].color.f37b * this.pointLights[i].intensity);
                        this.program.setUniformf(this.pointLightsPositionOffset + idx, this.pointLights[i].position);
                        if (this.pointLightsIntensityOffset >= 0) {
                            this.program.setUniformf(this.pointLightsIntensityOffset + idx, this.pointLights[i].intensity);
                        }
                        if (this.pointLightsSize <= 0) {
                            break;
                        }
                    }
                } else if (!this.lightsSet || !this.pointLights[i].equals((PointLight) points.get(i))) {
                    this.pointLights[i].set((PointLight) points.get(i));
                    idx = this.pointLightsLoc + (this.pointLightsSize * i);
                    this.program.setUniformf(this.pointLightsColorOffset + idx, this.pointLights[i].color.f39r * this.pointLights[i].intensity, this.pointLights[i].color.f38g * this.pointLights[i].intensity, this.pointLights[i].color.f37b * this.pointLights[i].intensity);
                    this.program.setUniformf(this.pointLightsPositionOffset + idx, this.pointLights[i].position);
                    if (this.pointLightsIntensityOffset >= 0) {
                        this.program.setUniformf(this.pointLightsIntensityOffset + idx, this.pointLights[i].intensity);
                    }
                    if (this.pointLightsSize <= 0) {
                        break;
                    }
                }
                i++;
            }
        }
        if (attributes.has(ColorAttribute.Fog)) {
            set(this.u_fogColor, ((ColorAttribute) attributes.get(ColorAttribute.Fog)).color);
        }
        if (lights.shadowMap != null) {
            set(this.u_shadowMapProjViewTrans, lights.shadowMap.getProjViewTrans());
            set(this.u_shadowTexture, lights.shadowMap.getDepthMap());
            set(this.u_shadowPCFOffset, 1.0f / (2.0f * ((float) lights.shadowMap.getDepthMap().texture.getWidth())));
        }
        this.lightsSet = true;
    }

    public void dispose() {
        this.program.dispose();
        super.dispose();
    }

    public int getDefaultCullFace() {
        return this.config.defaultCullFace == -1 ? defaultCullFace : this.config.defaultCullFace;
    }

    public void setDefaultCullFace(int cullFace) {
        this.config.defaultCullFace = cullFace;
    }

    public int getDefaultDepthFunc() {
        return this.config.defaultDepthFunc == -1 ? defaultDepthFunc : this.config.defaultDepthFunc;
    }

    public void setDefaultDepthFunc(int depthFunc) {
        this.config.defaultDepthFunc = depthFunc;
    }
}
