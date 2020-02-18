// module for control unit - handles different cases for switching between states
// inputs: Clk (Clock), Reset, ClearA_LoadB, Execute (aka Run), M (LSB of B)
// outputs: Shift_En (enable), ClearA (another reset), CA_LB (ClearA_LoadB), Addition (high or low), func_val
module control
(
			input logic Clk,
			input logic Reset, 
			input logic ClearA_LoadB,
			input logic Execute, 
			input logic M,
			output logic Shift_En,
			output logic ClearA,
			output logic CA_LB,
			output logic Addition,
			output logic func_val
);

	logic HIGH, LOW;
	assign HIGH = 1'b1;
	assign LOW = 1'b0;

    // Declare signals curr_state, next_state of type enum
	 // starting with A - add operations
	 // starting with S - shift operations
	enum logic [4:0] {Start, End, Ready, A1, S1, A2, S2, A3, S3, A4, S4, A5, S5, A6, S6, A7, S7, A8, S8}   curr_state, next_state; 

	//updates flip flop, current state is the only one
	always_ff @ (posedge Clk or posedge Reset)  
    begin
        if (Reset)
            curr_state <= Ready;
        else 
            curr_state <= next_state;
    end

    // Assign outputs based on state
	always_comb
    begin
        
		  next_state  = curr_state;	//required because I haven't enumerated all possibilities below
        unique case (curr_state) 
				
				// start in first add operation, which is A1
				Ready:	if(Execute)
								next_state = Start;
								
				Start:		next_state = A1;
				A1:			next_state = S1;
				S1:			next_state = A2;
				A2:			next_state = S2;
				S2:			next_state = A3;
				A3:			next_state = S3;
				S3:			next_state = A4;
				A4:			next_state = S4;
				S4:			next_state = A5;
				A5:			next_state = S5;
				S5:			next_state = A6;
				A6:			next_state = S6;
				S6:			next_state = A7;
				A7:			next_state = S7;
				S7:			next_state = A8;
				A8: 			next_state = S8;
				S8:			next_state = End;
				
				// idle state if not trying to execute and entire operation is finished
				End:		if(~Execute)
								next_state = Ready;
								
        endcase
   
		
		  // Assign outputs based on ‘state’
        case (curr_state) 
				
				// 1'b0 is low
				// 1'b1 is high
				
				// 'beginning state' - more like waiting until execute pressed
	   	   Ready: 
	         begin
                Shift_En <= LOW;
					 ClearA <= ClearA_LoadB;
					 CA_LB <= ClearA_LoadB;
					 Addition <= LOW;
					 func_val <= LOW;
		      end
				
				// once execute is pressed
	   	   Start: 
		      begin
					 Shift_En <= LOW;
					 ClearA <= HIGH;
					 CA_LB <= LOW;
					 Addition <= LOW;
					 func_val <= LOW;	      
		      end
	   	   
				// deals with addition/subtraction operations
		      A1,A2,A3,A4,A5,A6,A7,A8: 
				begin
					 Shift_En <= LOW;
					 ClearA <= LOW;
					 CA_LB <= LOW;
					 func_val <= LOW;
					 
					 // M high indicates addition
					 if(M) 
							Addition <= HIGH;
					 else 
							Addition <= LOW;
							
					 if(curr_state == A8)
							func_val <= HIGH;
				end
				
				// shift-enable high for shift operations
				S1,S2,S3,S4,S5,S6,S7,S8:
				begin
					 Shift_En <= HIGH;
					 ClearA <= LOW;
					 CA_LB <= LOW;
					 Addition <= LOW;
					 func_val <= LOW;
				 end
				
				// after final shift operation occurs
				End:
				begin
					 Shift_En <= LOW;
					 ClearA <= LOW;
					 CA_LB <= LOW;
					 Addition <= LOW;
					 func_val <= LOW;
			end
				 
				// default case - all zeros
			default:
			begin
					 Shift_En <= LOW;
					 ClearA <= LOW;
					 CA_LB <= LOW;
					 Addition <= LOW;
					 func_val <= LOW;
			end
				 
			endcase
			
		end

endmodule