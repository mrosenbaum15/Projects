//-------------------------------------------------------------------------
//    Ball.sv                                                            --
//    Viral Mehta                                                        --
//    Spring 2005                                                        --
//                                                                       --
//    Modified by Stephen Kempf 03-01-2006                               --
//                              03-12-2007                               --
//    Translated by Joe Meng    07-07-2013                               --
//    Modified by Po-Han Huang  12-08-2017                               --
//    Spring 2018 Distribution                                           --
//                                                                       --
//    For use with ECE 385 Lab 8                                         --
//    UIUC ECE Department                                                --
//-------------------------------------------------------------------------


module  ball ( input         Clk,                // 50 MHz clock
                             Reset,              // Active-high reset signal
                             frame_clk,          // The clock indicating a new frame (~60Hz)
               input [7:0]   keycode,
               input [9:0]   DrawX, DrawY,       // Current pixel coordinates
               output logic  is_ball,             // Whether current pixel belongs to ball or background
					output logic [3:0] LED_output
              );

    parameter [9:0] Ball_X_Center = 10'd320;  // Center position on the X axis
    parameter [9:0] Ball_Y_Center = 10'd240;  // Center position on the Y axis
    parameter [9:0] Ball_X_Min = 10'd0;       // Leftmost point on the X axis
    parameter [9:0] Ball_X_Max = 10'd639;     // Rightmost point on the X axis
    parameter [9:0] Ball_Y_Min = 10'd0;       // Topmost point on the Y axis
    parameter [9:0] Ball_Y_Max = 10'd479;     // Bottommost point on the Y axis
    parameter [9:0] Ball_X_Step = 10'd1;      // Step size on the X axis
    parameter [9:0] Ball_Y_Step = 10'd1;      // Step size on the Y axis
    parameter [9:0] Ball_Size = 10'd4;        // Ball size

    logic [9:0] Ball_X_Pos, Ball_X_Motion, Ball_Y_Pos, Ball_Y_Motion;
    logic [9:0] Ball_X_Pos_in, Ball_X_Motion_in, Ball_Y_Pos_in, Ball_Y_Motion_in;

    logic [7:0] a_key, s_key, d_key, w_key;

    always_comb begin
        a_key = 8'd4;
        s_key = 8'd22;
        d_key = 8'd7;
        w_key = 8'd26;
    end

    //////// Do not modify the always_ff blocks. ////////
    // Detect rising edge of frame_clk
    logic frame_clk_delayed, frame_clk_rising_edge;
    always_ff @ (posedge Clk) begin
        frame_clk_delayed <= frame_clk;
        frame_clk_rising_edge <= (frame_clk == 1'b1) && (frame_clk_delayed == 1'b0);
    end
    // Update registers
    always_ff @ (posedge Clk)
    begin
        if (Reset)
        begin
            Ball_X_Pos <= Ball_X_Center;
            Ball_Y_Pos <= Ball_Y_Center;
            Ball_X_Motion <= 10'd0;
            Ball_Y_Motion <= Ball_Y_Step;
        end
        else
        begin
            Ball_X_Pos <= Ball_X_Pos_in;
            Ball_Y_Pos <= Ball_Y_Pos_in;
            Ball_X_Motion <= Ball_X_Motion_in;
            Ball_Y_Motion <= Ball_Y_Motion_in;
        end

    end
    //////// Do not modify the always_ff blocks. ////////

    // void function to check boundaries
	 // this function will be used when adjusting the motion of the ball
  function void check_boundaries;
		
      if( Ball_Y_Pos + Ball_Size >= Ball_Y_Max ) begin // Ball is at the bottom edge, BOUNCE!
          Ball_Y_Motion_in = (~(Ball_Y_Step) + 1'b1);  // 2's complement.
			 Ball_X_Motion_in = 10'd0;
		end
      else if ( Ball_Y_Pos <= Ball_Y_Min + Ball_Size ) begin // Ball is at the top edge, BOUNCE!
          Ball_Y_Motion_in = Ball_Y_Step;
			 Ball_X_Motion_in = 10'd0;
		end
      else if( Ball_X_Pos + Ball_Size >= Ball_X_Max ) begin // Ball is at the right edge, BOUNCE!
          Ball_X_Motion_in = (~(Ball_X_Step) + 1'b1);  // 2's complement.
			 Ball_Y_Motion_in = 10'd0;
		end
      else if ( Ball_X_Pos <= Ball_X_Min + Ball_Size ) begin // Ball is at the left edge, BOUNCE!
          Ball_X_Motion_in = Ball_X_Step;
			 Ball_Y_Motion_in = 10'd0;
		end
  endfunction

    // You need to modify always_comb block.
    always_comb
    begin
        // By default, keep motion and position unchanged
        Ball_X_Pos_in = Ball_X_Pos;
        Ball_Y_Pos_in = Ball_Y_Pos;
        Ball_X_Motion_in = Ball_X_Motion;
        Ball_Y_Motion_in = Ball_Y_Motion;

        // Update position and motion only at rising edge of frame clock
        if (frame_clk_rising_edge)
        begin
            // Be careful when using comparators with "logic" datatype because compiler treats
            //   both sides of the operator as UNSIGNED numbers.
            // e.g. Ball_Y_Pos - Ball_Size <= Ball_Y_Min
            // If Ball_Y_Pos is 0, then Ball_Y_Pos - Ball_Size will not be -4, but rather a large positive number.
				
				// first ensuring ball is not on the edge
				check_boundaries();
				
				// reacting to keypresses - must check boundaries after changing direction
				case (keycode)
					 
					 // A is left
                a_key :
                    begin
                        Ball_X_Motion_in = (~(Ball_X_Step) + 1'b1);  // -1 in 2's complement.
                        Ball_Y_Motion_in = 10'd0; // only move in one direction
                        check_boundaries();
                    end
					 
					 // S is down
                s_key :
                    begin
                        Ball_X_Motion_in = 10'd0; // only move in one direction
                        Ball_Y_Motion_in = Ball_Y_Step; // down 1
                        check_boundaries();
                    end
					 
					 // D is right
                d_key :
                    begin
                        Ball_X_Motion_in = Ball_X_Step; // right 1
                        Ball_Y_Motion_in = 10'd0; // only move in one direction
                        check_boundaries();
                    end
					 
					 // W is up
                w_key :
                    begin
                        Ball_X_Motion_in = 10'd0; // only move in one direction
                        Ball_Y_Motion_in = (~(Ball_Y_Step) + 1'b1);  // 2's complement.
                        check_boundaries();
                    end

    			default : // not changing direction here
                    begin
                        Ball_X_Motion_in = Ball_X_Motion_in; 
                        Ball_Y_Motion_in = Ball_Y_Motion_in;
                    end

    		endcase

            // Update the ball's position with its motion
            Ball_X_Pos_in = Ball_X_Pos + Ball_X_Motion;
            Ball_Y_Pos_in = Ball_Y_Pos + Ball_Y_Motion;
        end

        /**************************************************************************************
            ATTENTION! Please answer the following quesiton in your lab report! Points will be allocated for the answers!
            Hidden Question #2/2:
               Notice that Ball_Y_Pos is updated using Ball_Y_Motion.
              Will the new value of Ball_Y_Motion be used when Ball_Y_Pos is updated, or the old?
              What is the difference between writing
                "Ball_Y_Pos_in = Ball_Y_Pos + Ball_Y_Motion;" and
                "Ball_Y_Pos_in = Ball_Y_Pos + Ball_Y_Motion_in;"?
              How will this impact behavior of the ball during a bounce, and how might that interact with a response to a keypress?
              Give an answer in your Post-Lab.
        **************************************************************************************/
    end

    // Compute whether the pixel corresponds to ball or background
    /* Since the multiplicants are required to be signed, we have to first cast them
       from logic to int (signed by default) before they are multiplied. */
    int DistX, DistY, Size;
    assign DistX = DrawX - Ball_X_Pos;
    assign DistY = DrawY - Ball_Y_Pos;
    assign Size = Ball_Size;
    always_comb begin
        if ( ( DistX*DistX + DistY*DistY) <= (Size*Size) )
            is_ball = 1'b1;
        else
            is_ball = 1'b0;
        /* The ball's (pixelated) circle is generated using the standard circle formula.  Note that while
           the single line is quite powerful descriptively, it causes the synthesis tool to use up three
           of the 12 available multipliers on the chip! */
		  
		  /*
				Setting the LEDs on the board.
				LED0 is for left
				LED1 is for down
				LED2 is for right
				LED3 is for up
		  */
		  if(Ball_X_Motion == 10'd1)
				LED_output = 4'b0100;
		  else if(Ball_Y_Motion == 10'd1)
				LED_output = 4'b0010;
		  else if(Ball_X_Motion == (~(Ball_X_Step) + 1'b1) )
				LED_output = 4'b0001;
		  else 
				LED_output = 4'b1000;
    end

endmodule
