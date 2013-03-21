'use strict';

/**
 * 
 * @param $scope
 * @returns
 */
function CarObject($scope) {
	
	car_pos = new b2Vec2(3, 3);
	car_dim = new b2Vec2(0.2, 0.35);
	car.body = createBox(world, car_pos.x, car_pos.y, car_dim.x, car_dim.y, {
		'linearDamping' : 10.0,
		'angularDamping' : 10.0
	});

	var wheel_dim = car_dim.Copy();
	wheel_dim.Multiply(0.2);

	// front wheels
	left_wheel = createBox(world, car_pos.x - car_dim.x, car_pos.y + car_dim.y / 2, wheel_dim.x, wheel_dim.y, {});
	right_wheel = createBox(world, car_pos.x + car_dim.x, car_pos.y + car_dim.y / 2, wheel_dim.x, wheel_dim.y, {});

	// rear wheels
	left_rear_wheel = createBox(world, car_pos.x - car_dim.x, car_pos.y - car_dim.y / 2, wheel_dim.x, wheel_dim.y, {});
	right_rear_wheel = createBox(world, car_pos.x + car_dim.x, car_pos.y - car_dim.y / 2, wheel_dim.x, wheel_dim.y, {});

	var front_wheels = {
		'left_wheel' : left_wheel,
		'right_wheel' : right_wheel
	};

	for ( var i in front_wheels) {
		var wheel = front_wheels[i];

		var joint_def = new b2RevoluteJointDef();
		joint_def.Initialize(car.body, wheel, wheel.GetWorldCenter());

		// after enablemotor , setmotorspeed is used to make the joins rotate ,
		// remember!
		joint_def.enableMotor = true;
		joint_def.maxMotorTorque = 100000;

		// this will prevent spinning of wheels when hit by something strong
		joint_def.enableLimit = true;
		joint_def.lowerAngle = -1 * max_steer_angle;
		joint_def.upperAngle = max_steer_angle;

		// create and save the joint
		car[i + '_joint'] = world.CreateJoint(joint_def);
	}

	var rear_wheels = {
		'left_rear_wheel' : left_rear_wheel,
		'right_rear_wheel' : right_rear_wheel
	};

	for ( var i in rear_wheels) {
		var wheel = rear_wheels[i];

		var joint_def = new b2PrismaticJointDef();
		joint_def.Initialize(car.body, wheel, wheel.GetWorldCenter(),
				new b2Vec2(1, 0));

		joint_def.enableLimit = true;
		joint_def.lowerTranslation = joint_def.upperTranslation = 0.0;

		car[i + '_joint'] = world.CreateJoint(joint_def);
	}

	car.left_wheel = left_wheel;
	car.right_wheel = right_wheel;
	car.left_rear_wheel = left_rear_wheel;
	car.right_rear_wheel = right_rear_wheel;

	return car;
}