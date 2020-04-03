module control_unit
(
		input logic CLK,
		input logic RESET,
		input logic AES_START,
		input logic [3:0] num_round, mc_round, instr_round,
		input logic [4:0] key_round,
		output logic [3:0] round_reset, round_bool,
		output logic AES_DONE, Load_R0, Load_Key
);


// Declare signals curr_state, next_state of type enum
// starting with A - add operations
// starting with S - shift operations
enum logic [3:0] { Halted, key_expansion, finished, mc_inv, r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10 }
						curr_state, next_state;

logic HIGH, LOW;
assign HIGH = 1'b1;
assign LOW = 1'b0;

//updates flip flop, current state is the only one
always_ff @ (posedge CLK) begin
 
	if (RESET)
		curr_state <= Halted;
	else 
		curr_state <= next_state;
		
end

// 0 - round, 1 - key, 2 - instruction, 3 - mix columns
always_comb begin

	next_state = curr_state;
	round_reset = 4'b1110;
	round_bool = 4'b0000;
	AES_DONE = LOW;
	Load_R0 = LOW;
	Load_Key = LOW;
	
	unique case(curr_state)
	
		Halted:
				if(AES_START)
					next_state = key_expansion;
					
		key_expansion:
				if(key_round == 5'd24)
					next_state = r0;

		r0:	
				if(instr_round == 5'd2)
					next_state = r1;
					
		r1, r2, r3, r4, r5, r6, r7, r8, r9:
				if(instr_round == 5'd3)
					next_state = mc_inv;
					
		mc_inv:
				
				if(mc_round == 5'd4) begin
					
					case(num_round)
						
						5'd2:	 next_state = r2;
						5'd3:  next_state = r3;
						5'd4:  next_state = r4;
						5'd5:	 next_state = r5;
						5'd6:  next_state = r6;
						5'd7:  next_state = r7;
						5'd8:  next_state = r8;
						5'd9:  next_state = r9;
						5'd10: next_state = r10;
						default: ;
						
					endcase
					
				end
		r10:
				if(instr_round == 5'd3)
					next_state = finished;
		
		finished:		
				if(AES_START != 1)
					next_state = Halted;
		
		default: 		next_state = Halted;

//		r1:
//				if(instr_round == 5'd3)
//					next_state = mc_inv;
//					
//		r2:	
//				if(instr_round == 5'd3)
//					next_state = mc_inv;
//		
//		r3:	
//				if(instr_round == 5'd3)
//					next_state = mc_inv;
//					
//		r4:	
//				if(instr_round == 5'd3)
//					next_state = mc_inv;
//					
//		r5:	
//				if(instr_round == 5'd3)
//					next_state = mc_inv;
//					
//		r6:	
//				if(instr_round == 5'd3)
//					next_state = mc_inv;
//		
//		r7:	
//				if(instr_round == 5'd3)
//					next_state = mc_inv;
//					
//		r8:	
//				if(instr_round == 5'd3)
//					next_state = mc_inv;
//					
//		r9:	
//				if(instr_round == 5'd3)
//					next_state = r2;
	endcase
	
	case(curr_state)
		
		Halted:	;
		
		key_expansion:	
		
				if(key_round == 5'd24)
					round_bool[1] = LOW;
				else begin
					round_bool[1] = HIGH;
					Load_R0 = HIGH;
				end
				
		r0:
		
				if(instr_round == 5'd2) begin
					round_reset[2] = HIGH;
					round_bool[2] = LOW;
					Load_Key = HIGH;
					round_bool[0] = HIGH;
				end
				
				else begin
					round_reset[2] = LOW;
					round_bool[2] = HIGH;
					Load_Key = LOW;
					round_reset[0] = HIGH;
					round_bool[0] = LOW;
				end
				
		r1, r2, r3, r4, r5, r6, r7, r8, r9, r10:
				
				if(instr_round == 5'd3) begin
					round_reset[2] = LOW;
					round_bool[2] = LOW;
					Load_Key = LOW;
					round_bool[0] = HIGH;
				end
				
				else begin
					round_reset[2] = LOW;
					round_bool[2] = HIGH;
					Load_Key = HIGH;
					round_bool[0] = LOW;
				end
				
		mc_inv:
		
				if(mc_round == 5'd4) begin
					round_reset[2] = HIGH;
					round_reset[3] = HIGH;
					round_bool[3] = LOW;
					Load_Key = HIGH;
					round_reset[0] = LOW;
				end
				
				else begin
					round_reset[2] = LOW;
					round_reset[3] = LOW;
					round_bool[3] = HIGH;
					Load_Key= LOW;
					round_reset[0] = LOW;
					round_bool[0] = LOW;
				end
				
		finished:		AES_DONE = HIGH;
		
	endcase
	
end

endmodule