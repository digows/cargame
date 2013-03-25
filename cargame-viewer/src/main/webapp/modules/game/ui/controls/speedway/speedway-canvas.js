'use strict';

/**
 * 
 * @param src
 * @param x
 * @param y
 * @returns
 */
function Car(id, x, y, angle, playerName, imageSource) {
	
	this.id = id;
    this.playerName = playerName;
    
    this.x = x;
    this.y = y;
    this.angle = angle;
    
    this.imageSource = imageSource != null ? imageSource : Car.DEFAULT_IMAGE;
};
Car.DEFAULT_IMAGE = "assets/images/car.png";

/**
 * 
 * @param canvas
 */
function SpeedwayCanvas(canvas) {
	
	var LOG = new Log(Log.DEBUG, Log.consoleLogger);
	LOG.debug("SpeedwayCanvas initialized");
	
	var context = canvas.getContext('2d');
	
	/**
	 * 
	 */
	this.clear = function() {
		// Store the current transformation matrix
		context.save();

		// Use the identity matrix while clearing the canvas
		context.setTransform(1, 0, 0, 1, 0, 0);
		context.clearRect(0, 0, canvas.width, canvas.height);

		// Restore the transform
		context.restore();
	};
	
	/**
	 * 
	 */
	this.drawCar = function(car) {
		LOG.debug("Drawing car..."+car);
        
        var carImage = new Image();
        carImage.src = car.imageSource != null ? car.imageSource : Car.DEFAULT_IMAGE;
        carImage.onload = function() {
        	
	    	context.save();
	    	context.fillStyle = "red";
	    	context.font = "14px Helvetica";
	    	context.textBaseline = "middle";
	    	
	    	switch (car.angle) {
				case 0: {
					context.drawImage(carImage, car.x, car.y);
					context.fillText(car.playerName, car.x, car.y+(carImage.height)/2, carImage.width);
					break;
				}
				case 90: {
					context.translate(car.x, car.y);
		        	context.rotate(car.angle * Math.PI/180);
					context.drawImage(carImage, 0, -carImage.height);
					context.fillText(car.playerName, 0, -carImage.height/2);
					break;
				}
				case 180: {
					context.translate(car.x, car.y);
					context.rotate(car.angle * Math.PI/180);
					context.drawImage(carImage, -carImage.width, -carImage.height);
					context.fillText(car.playerName, -carImage.width, -carImage.height/2);
					break;
				}
				case 270: {
					context.translate(car.x, car.y);
					context.rotate(car.angle * Math.PI/180);
					context.drawImage(carImage, -carImage.width, -carImage.width);
					context.fillText(car.playerName, -carImage.width, (-carImage.height/2)+(carImage.height/2));
					break;
				}
				default: {
					break;					
				}
			}
        	
        	context.restore();
        };
	};
};
