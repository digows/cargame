'use strict';

/**
 * 
 */
function Car() {

	this.top_engine_speed = 2.5;
	this.engine_on = false;
	this.gear = 1;
	this.engine_speed = 0;
	this.body = null;
}

/**
 * 
 */
function Game() {

	this.ctx = null;
	this.canvas_width = 0;
	this.canvas_height = 0;
	this.screen_width = 0;
	this.screen_height = 0;
	
	this.screen_width = 0;
	this.screen_height = 0;
};

/**
 * 
 * @param canvas
 */
function SpeedwayCanvas(canvas) {

	// 1 metre of box2d length becomes 100 pixels on canvas
	this.scale = 100;
	this.steer_speed = 1.0;
	this.steering_angle = 0;
	this.max_steer_angle = Math.PI / 3; // 60 degrees to be precise

	this.car = new Car();
	
	this.game = new Game();
	this.game.ctx = canvas.getContext('2d');

	this.game.canvas_width = parseInt(canvas.width);
	this.game.canvas_height = parseInt(canvas.height);

	this.game.screen_width = this.game.canvas_width / this.scale;
	this.game.screen_height = this.game.canvas_height / this.scale;

	this.world = null;

	/**
	 * 
	 */
	this.start = function() {
		// first create the world
		this.createWorld();

		this.create_car();

		// Start the Game Loop!!!!!!!
		this.game_loop();
	};

	/**
	 * 
	 */
	this.createWorld = function () {

		var gravity = new Box2D.Common.Math.b2Vec2(0, 0);
		var doSleep = true;

		this.world = new Box2D.Dynamics.b2World(gravity, doSleep);

		// setup debug draw
		var debugDraw = new Box2D.Dynamics.b2DebugDraw();
		debugDraw.SetSprite(this.game.ctx);
		debugDraw.SetDrawScale(this.scale);
		debugDraw.SetFillAlpha(0.5);
		debugDraw.SetLineThickness(1.0);
		debugDraw.SetFlags(Box2D.Dynamics.b2DebugDraw.e_shapeBit | Box2D.Dynamics.b2DebugDraw.e_jointBit);
		this.world.SetDebugDraw(debugDraw);

		this.createBox(this.world, this.game.screen_width / 2, 0.5,
				this.game.screen_width / 2 - 1, 0.1, {
					'type' : Box2D.Dynamics.b2Body.b2_staticBody,
					'restitution' : 0.5
				});

		this.createBox(this.world, this.game.screen_width - 1,
				this.game.screen_height / 2, 0.1,
				this.game.screen_height / 2 - 1, {
					'type' : Box2D.Dynamics.b2Body.b2_staticBody,
					'restitution' : 0.5
				});

		// few lightweight boxes
		var free = {
			'restitution' : 1.0,
			'linearDamping' : 1.0,
			'angularDamping' : 1.0,
			'density' : 0.2
		};

		this.createBox(this.world, 2, 2, 0.5, 0.5, free);
		this.createBox(this.world, 5, 2, 0.5, 0.5, free);
	};

	/**
	 * Create standard boxes of given height , width at x,y
	 */
	this.createBox = function (world, x, y, width, height, options) {
		// default setting
		options = $.extend(true, {
			'density' : 1.0,
			'friction' : 0.0,
			'restitution' : 0.2,

			'linearDamping' : 0.0,
			'angularDamping' : 0.0,

			'gravityScale' : 1.0,
			'type' : Box2D.Dynamics.b2Body.b2_dynamicBody
		}, options);

		var body_def = new Box2D.Dynamics.b2BodyDef();
		var fix_def = new Box2D.Dynamics.b2FixtureDef;

		fix_def.density = options.density;
		fix_def.friction = options.friction;
		fix_def.restitution = options.restitution;

		fix_def.shape = new Box2D.Collision.Shapes.b2PolygonShape();

		fix_def.shape.SetAsBox(width, height);

		body_def.position.Set(x, y);

		body_def.linearDamping = options.linearDamping;
		body_def.angularDamping = options.angularDamping;

		body_def.type = options.type;

		var b = world.CreateBody(body_def);
		b.CreateFixture(fix_def);

		return b;
	};
	
	/**
	 * 
	 */
	this.create_car = function () {
		
		var car_pos = new Box2D.Common.Math.b2Vec2(3, 3);
		var car_dim = new Box2D.Common.Math.b2Vec2(0.2, 0.35);
		
		this.car.body = this.createBox(this.world, car_pos.x, car_pos.y, car_dim.x, car_dim.y, {
			'linearDamping' : 10.0,
			'angularDamping' : 10.0
		});

		var wheel_dim = car_dim.Copy();
		wheel_dim.Multiply(0.2);

		// front wheels
		var left_wheel = this.createBox(this.world, car_pos.x - car_dim.x, car_pos.y + car_dim.y / 2, wheel_dim.x, wheel_dim.y, {});
		var right_wheel = this.createBox(this.world, car_pos.x + car_dim.x, car_pos.y + car_dim.y / 2, wheel_dim.x, wheel_dim.y, {});

		// rear wheels
		var left_rear_wheel = this.createBox(this.world, car_pos.x - car_dim.x, car_pos.y - car_dim.y / 2, wheel_dim.x, wheel_dim.y, {});
		var right_rear_wheel = this.createBox(this.world, car_pos.x + car_dim.x, car_pos.y - car_dim.y / 2, wheel_dim.x, wheel_dim.y, {});

		var front_wheels = {
			'left_wheel' : left_wheel,
			'right_wheel' : right_wheel
		};

		for ( var i in front_wheels) {
			var wheel = front_wheels[i];

			var joint_def = new Box2D.Dynamics.Joints.b2RevoluteJointDef();
			joint_def.Initialize(this.car.body, wheel, wheel.GetWorldCenter());

			// after enablemotor , setmotorspeed is used to make the joins rotate ,
			// remember!
			joint_def.enableMotor = true;
			joint_def.maxMotorTorque = 100000;

			// this will prevent spinning of wheels when hit by something strong
			joint_def.enableLimit = true;
			joint_def.lowerAngle = -1 * this.max_steer_angle;
			joint_def.upperAngle = this.max_steer_angle;

			// create and save the joint
			this.car[i + '_joint'] = this.world.CreateJoint(joint_def);
		}

		var rear_wheels = {
			'left_rear_wheel' : left_rear_wheel,
			'right_rear_wheel' : right_rear_wheel
		};

		for ( var i in rear_wheels) {
			var wheel = rear_wheels[i];

			var joint_def = new Box2D.Dynamics.Joints.b2PrismaticJointDef();
			joint_def.Initialize(this.car.body, wheel, wheel.GetWorldCenter(), new Box2D.Common.Math.b2Vec2(1, 0));

			joint_def.enableLimit = true;
			joint_def.lowerTranslation = joint_def.upperTranslation = 0.0;

			this.car[i + '_joint'] = this.world.CreateJoint(joint_def);
		}

		this.car.left_wheel = left_wheel;
		this.car.right_wheel = right_wheel;
		this.car.left_rear_wheel = left_rear_wheel;
		this.car.right_rear_wheel = right_rear_wheel;
	};
	
	/**
	 * Method to update the car 
	 */
	this.update_car = function () {
		var wheels = [ 'left', 'right' ];

		// Driving
		for ( var i in wheels) {
			var d = wheels[i] + '_wheel';
			var wheel = this.car[d];

			// get the direction in which the wheel is pointing
			var direction = wheel.GetTransform().R.col2.Copy();
			// console.log(direction.y);
			direction.Multiply(this.car.engine_speed);

			// apply force in that direction
			wheel.ApplyForce(direction, wheel.GetPosition());
		}

		// Steering
		for ( var i in wheels) {
			var d = wheels[i] + '_wheel_joint';
			var wheel_joint = this.car[d];

			// max speed - current speed , should be the motor speed , so when max
			// speed reached , speed = 0;
			var angle_diff = this.steering_angle - wheel_joint.GetJointAngle();
			wheel_joint.SetMotorSpeed(angle_diff * this.steer_speed);
		}
	};
	
	/**
	 * Draw a world this method is called in a loop to redraw the world
	 */
	this.redraw_world = function (world) {
		// convert the canvas coordinate directions to cartesian
		this.game.ctx.save();
		this.game.ctx.translate(0, this.game.canvas_height);
		this.game.ctx.scale(1, -1);
		this.world.DrawDebugData();
		this.game.ctx.restore();
	};
	
	/**
	 * This method will draw the world again and again called by settimeout , self
	 * looped , game_loop
	 */
	this.game_loop = function () {
		var fps = 60;
		var time_step = 1.0 / fps;

		this.update_car();
		
		// move the world ahead , step ahead man!!
		this.world.Step(time_step, 8, 3);
		// Clear the forces , Box2d 2.1a
		this.world.ClearForces();

		// redraw the world
		this.redraw_world(this.world);

		// call this function again after 10 seconds
		//setTimeout(this.game_loop, 1000 / 60);
	};
};
