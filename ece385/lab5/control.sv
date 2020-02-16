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
			output logic Subtraction
);

    // Declare signals curr_state, next_state of type enum
	enum logic [4:0] {Start, End, Load, Restart, Nothing, A, AA, B, BB, C, CC, D, DD, E, EE, F, FF, G, GG, H, HH}   curr_state, next_state; 

	//updates flip flop, current state is the only one
	always_ff @ (posedge Clk or posedge Reset)  
    begin
        if (Reset)
            curr_state <= Start;
        else 
            curr_state <= next_state;
    end

    // Assign outputs based on state
	always_comb
    begin
        
		  next_state  = curr_state;	//required because I haven't enumerated all possibilities below
        unique case (curr_state) 
		  
				Start:	if(Execute)
								next_state = A;
							else if(CA_LB)
								next_state = Load;
				Load:		next_state = Start;
				
				A:			next_state = AA;
				AA:		next_state = B;
				B:			next_state = BB;
				BB:		next_state = C;
				C:			next_state = CC;
				CC:		next_state = D;
				D:			next_state = DD;
				DD:		next_state = E;
				E:			next_state = EE;
				EE:		next_state = F;
				F:			next_state = FF;
				FF:		next_state = G;
				G:			next_state = GG;
				GG:		next_state = H;
				H: 		next_state = HH;
				HH:		next_state = End;
				
				End:		if(~Execute)
								next_state = Nothing;
				
				Nothing:	if(Execute)
									next_state = Restart;
									
				Restart: next_state = A;
				
        endcase
   
		
		  // Assign outputs based on ‘state’
        case (curr_state) 
	   	   Start, Nothing, End: 
	         begin
                Shift_En <= 1'b0;
					 ClearA <= 1'b0;
					 CA_LB <= 1'b0;
					 Addition <= 1'b0;
					 Subtraction <= 1'b0;	 
		      end
				
	   	   Load: 
		      begin
					 Shift_En <= 1'b0;
					 ClearA <= 1'b0;
					 CA_LB <= 1'b1;
					 Addition <= 1'b0;
					 Subtraction <= 1'b0;	      
		      end
	   	   
		      A,
				B,
				C,
				D,
				E,
				F,
				G: 
				begin
					 Shift_En <= 1'b0;
					 ClearA <= 1'b0;
					 CA_LB <= 1'b0;
					 
					 if(M) begin
							Addition <= 1'b1;
							if(curr_state == H) 
								Subtraction <= 1'b1;
							else
								Subtraction <= 1'b0;
					 end
							
					 else begin
							Addition <= 1'b0;
							Subtraction <= 1'b0;
					 end
				end
			
			AA,
			BB,
			CC,
			DD,
			EE,
			FF,
			GG,
			HH: begin
					 Shift_En <= 1'b1;
					 ClearA <= 1'b0;
					 CA_LB <= 1'b0;
					 Addition <= 1'b0;
					 Subtraction <= 1'b0;
				 end
				 
			Restart: begin
					 Shift_En <= 1'b0;
					 ClearA <= 1'b1;
					 CA_LB <= 1'b0;
					 Addition <= 1'b0;
					 Subtraction <= 1'b0;
				 end
				
			default: begin
					 Shift_En <= 1'b0;
					 ClearA <= 1'b0;
					 CA_LB <= 1'b0;
					 Addition <= 1'b0;
					 Subtraction <= 1'b0;
				 end
				 
			endcase
			
		end

endmodule