package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by wangzw on 2017/8/2.
 */

public class LearnBox2d extends ApplicationAdapter {

    /**
     * World掌管Box2d创建所有物理实体，动态模拟，异步查询。也包含有效的内存管理工具
     */
    World world;
    /**
     * Box2d只是模拟世界，并不会像屏幕上（Stage之类上）添加任何显示对象，但是Libgdx提供了Box2DDebugRenderer
     * 供我们模拟，里面包含了ShapeRenderer，进行模拟调试
     */
    Box2DDebugRenderer box2DDebugRenderer;
    Body body;
    OrthographicCamera camera;

    private static final float SCENE_WIDTH = 12.8f; // 13 metres wide
    private static final float SCENE_HEIGHT = 7.2f; // 7 metres high

    @Override
    public void create() {
        super.create();
        // 在X轴方向上受力为0， 在Y轴方向上受到向下的重力 9.8
        world = new World(new Vector2(0.0f, -9.8f), true);
        box2DDebugRenderer = new Box2DDebugRenderer();

        /** BodyDef 定义创建刚体所需要的全部数据。可以被重复使用创建不同刚体，BodyDef之后需要绑定Shape **/
        BodyDef bodyDef = new BodyDef();
        /** 动态刚体，受力之后运动会发生改变。 默认创建的是静态BodyType.StaticBody **/
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(SCENE_WIDTH / 2, SCENE_HEIGHT / 2);
//        bodyDef.linearVelocity.set(2.0f, 0f);

        body = world.createBody(bodyDef);

        // 刚体没有任何显示，Shape主要用来显示和做碰撞检测。
        PolygonShape shape = new PolygonShape(); // 多边形
        shape.setAsBox(0.5f, 0.5f);  // 半个宽度和半个高度作为参数，这样盒子就是一米宽，一米高

        // Fixture(夹具)，将形状绑定到物体上，并添加密度(density)、摩擦(friction)、恢复(restitution)等材料特性
        // 还将形状放入到碰撞检测系统中(Broad Phase)，以使之能与其它形状相碰撞
        // 一个物体和另一个物体碰撞，碰撞后速度和碰撞前速度的比值会保持不变，这比值就叫恢复系数
        FixtureDef fixtureDef = new FixtureDef();

        /** 当你使用 fixture 向 body 添加 shape 的时候， shape 的坐标对于 body 来说就变成本地的了。因此
         当 body 移动的时候， shape 也一起移动。 fixture 的世界变换继承自它的父 body。 fixture 没有独立
         于 body 的变换。所以我们不需要移动 body 上的 shape。不支持移动或修改 body 上的 shape。原因很简单：
         形状发生改变的物体不是刚体，而 Box2D 只是个刚体引擎。 Box2D 所做的很多假设都是基于刚体模型的。
         如果这一条被改变的话，很多事情都会出错。
         **/
        fixtureDef.shape = shape;
        fixtureDef.density = 2;
        fixtureDef.restitution = 0.8f;  // 恢复系数，物理受到反作用力的运动情况，值越大反向运动速度越快

        body.createFixture(fixtureDef);
        shape.dispose();

        camera = new OrthographicCamera(SCENE_WIDTH, SCENE_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        createGround();

        Gdx.app.log("FY", "x=" + body.getWorldCenter().x);
    }

    @Override
    public void render() {
        world.step(1 / 45f, 6, 2);

        Gdx.gl.glClearColor(0.39f, 0.58f, 0.92f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // camera指定了Worl的大小
        box2DDebugRenderer.render(world, camera.combined);
    }

    @Override
    public void dispose() {
        world.dispose();
        box2DDebugRenderer.dispose();
    }

    private void createGround() {

        float halfGroundWidth = SCENE_WIDTH;
        float halfGroundHeight = 0.5f;  // 0.5 * 2 = 1meter

        // Create a static body definition, 没有速度，不接受力和冲量的作用
        BodyDef groundBodyDef = new BodyDef();
        // default is StaticBody
//        groundBodyDef.type = BodyDef.BodyType.StaticBody;

        // Set the ground position (origin)
        groundBodyDef.position.set(halfGroundWidth * 0.5f, halfGroundHeight);

        // Create a body from the defintion and add it to the world
        Body groundBody = world.createBody(groundBodyDef);

        // (setAsBox takes half-width and half-height as arguments)
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(halfGroundWidth * 0.5f, halfGroundHeight);

        /** Body是没有形状的,必须绑定具体形状才能显示，一般有2个方法，如下：
         *  1. createFixture (FixtureDef def) 创建带有摩擦力，恢复系数等材料特性的Body
         *  2. createFixture (Shape shape, float density) 直接根据形状创建Body，不带有材料特性，density = 0
         * **/
        groundBody.createFixture(groundBox, 1.0f);
        // Free resources
        groundBox.dispose();

    }
}
