//------------------------------------------------------------------------------
// Company:          UIUC ECE Dept.
// Engineer:         Stephen Kempf
//
// Create Date:    17:44:03 10/08/06
// Design Name:    ECE 385 Lab 6 Given Code - Incomplete ISDU
// Module Name:    ISDU - Behavioral
//
// Comments:
//    Revised 03-22-2007
//    Spring 2007 Distribution
//    Revised 07-26-2013
//    Spring 2015 Distribution
//    Revised 02-13-2017
//    Spring 2017 Distribution
//------------------------------------------------------------------------------


module ISDU (   input logic         Clk,
									Reset,
									Run,
									Continue,

				input logic[3:0]    Opcode,
				input logic         IR_5,
				input logic         IR_11,
				input logic         BEN,

				output logic        LD_MAR,
									LD_MDR,
									LD_IR,
									LD_BEN,
									LD_CC,
									LD_REG,
									LD_PC,
									LD_LED, // for PAUSE instruction

				output logic        GatePC,
									GateMDR,
									GateALU,
									GateMARMUX,

				output logic [1:0]  PCMUX,
				output logic        DRMUX,
									SR1MUX,
									SR2MUX,
									ADDR1MUX,
				output logic [1:0]  ADDR2MUX,
									ALUK,

				output logic        Mem_CE,
									Mem_UB,
									Mem_LB,
									Mem_OE,
									Mem_WE
				);

	// finished the next states based off the given diagram in the lab manual
	enum logic [4:0] {  Halted,
						PauseIR1,
						PauseIR2,
						S_18,
						S_33_1,
						S_33_2,
						S_35,
						S_32,
						S_01,
						S_05,
						S_09,
						S_06,
						S_25_1,
						S_25_2,
						S_27,
						S_07,
						S_23,
						S_16_1,
						S_16_2,
						S_04,
						S_21,
						S_12,
						S_00,
						S_22 }   State, Next_state;   // Internal state logic

	always_ff @ (posedge Clk)
	begin
		if (Reset)
			State <= Halted;
		else
			State <= Next_state;
	end
	
	// much easier to consistently type HIGH and LOW
	logic HIGH, LOW;
	assign HIGH = 1'b1;
	assign LOW = 1'b0;

	always_comb
	begin
		// Default next state is staying at current state
		Next_state = State;

		// Default controls signal values
		LD_MAR = 1'b0;
		LD_MDR = 1'b0;
		LD_IR = 1'b0;
		LD_BEN = 1'b0;
		LD_CC = 1'b0;
		LD_REG = 1'b0;
		LD_PC = 1'b0;
		LD_LED = 1'b0;

		GatePC = 1'b0;
		GateMDR = 1'b0;
		GateALU = 1'b0;
		GateMARMUX = 1'b0;

		ALUK = 2'b00;

		PCMUX = 2'b00;
		DRMUX = 1'b0;
		SR1MUX = 1'b0;
		SR2MUX = 1'b0;
		ADDR1MUX = 1'b0;
		ADDR2MUX = 2'b00;

		Mem_OE = 1'b1;
		Mem_WE = 1'b1;

		// Assign next state
		unique case (State)
			Halted :
				if (Run)
					Next_state = S_18;
			S_18 :
				Next_state = S_33_1;
			// Any states involving SRAM require more than one clock cycles.
			// The exact number will be discussed in lecture.
			S_33_1 :
				Next_state = S_33_2;
			S_33_2 :
				Next_state = S_35;
			S_35 :
				Next_state = S_32;
			// PauseIR1 and PauseIR2 are only for Week 1 such that TAs can see
			// the values in IR.
			PauseIR1 :
				if (~Continue)
					Next_state = PauseIR1;
				else
					Next_state = PauseIR2;
			PauseIR2 :
				if (Continue)
					Next_state = PauseIR2;
				else
					Next_state = S_18;
					
			// handling all possible states from S_32 as given from LC3 diagram
			S_32 :
				case (Opcode)
					4'd1 :
						Next_state = S_01;

					4'd5 :
						Next_state = S_05;

					4'd9 :
						Next_state = S_09;

					4'd6 :
						Next_state = S_06;

					4'd7 :
						Next_state = S_07;

					4'd4 :
						Next_state = S_04;

					4'd12 :
						Next_state = S_12;

					4'd0 :
						Next_state = S_00;
						
					4'd13 :
						Next_state = PauseIR1; // pause state to show LEDs
						
					default :
						Next_state = S_18;
				endcase

			// all of these states go back to the start (S_18)
			S_01, S_05, S_09, S_27, S_16_2, S_21, S_12, S_22 :
				Next_state = S_18;
	
			// the following next_state logic is taken from state diagram
			S_06 :
				Next_state = S_25_1;

			S_25_1 :
				Next_state = S_25_2;

			S_25_2 :
				Next_state = S_27;

			S_07 :
				Next_state = S_23;

			S_23 :
				Next_state = S_16_1;

			S_16_1 :
				Next_state = S_16_2;

			S_04 :
				Next_state = S_21;

			S_00 :
				if(BEN == HIGH)
					Next_state = S_22;
				else
					Next_state = S_18;
			// You need to finish the rest of states.....

			default : ;

		endcase

		// Assign control signals based on current state
		case (State)
			Halted: ;
			S_18 :
				begin
					GatePC = 1'b1;
					LD_MAR = 1'b1;
					PCMUX = 2'b00;
					LD_PC = 1'b1;
				end
			S_33_1 :
				Mem_OE = 1'b0;
			S_33_2 :
				begin
					Mem_OE = 1'b0;
					LD_MDR = 1'b1;
				end
			S_35 :
				begin
					GateMDR = 1'b1;
					LD_IR = 1'b1;
				end
			// need to display LEDs in paused state
			PauseIR1:
				begin
					LD_LED = HIGH;
				end
			PauseIR2: ;
			S_32 :
				LD_BEN = 1'b1;
			// ADD
			S_01 :
				begin
					SR1MUX = HIGH;
					SR2MUX = IR_5;
					DRMUX = LOW;
					ALUK = 2'd0;
					GateALU = HIGH;
					LD_REG = HIGH;
					LD_CC = HIGH;
				end
			// AND
			S_05 :
				begin
					SR1MUX = HIGH;
					SR2MUX = IR_5;
					DRMUX = LOW;
					ALUK = 2'd1;
					GateALU = HIGH;
					LD_REG = HIGH;
					LD_CC = HIGH;
				end
			
			// NOT
			S_09 :
				begin
					SR1MUX = HIGH;
					SR2MUX = LOW;
					DRMUX = LOW;
					ALUK = 2'd2;
					GateALU = HIGH;
					LD_REG = HIGH;
					LD_CC = HIGH;
				end
			// MAR <- B + off6
			S_06, S_07 :
				begin
					ADDR1MUX = HIGH;
					ADDR2MUX = 2'd1;
					SR1MUX = HIGH;
					GateMARMUX = HIGH;
					LD_MAR = HIGH;
				end
			
			// MDR <- M[MAR]
			S_25_1, S_25_2 :
				begin
					Mem_OE = LOW;
					Mem_WE = HIGH;

					if(State == S_25_2)
						LD_MDR = HIGH;
				end
			
			// DR <- MDR
			S_27 :
				begin
					DRMUX = LOW;
					GateMDR = HIGH;
					LD_REG = HIGH;
					LD_CC = HIGH;
				end
				
			// MDR <- SR
			S_23 :
				begin
					SR1MUX = LOW;
					ALUK = 2'd3;
					GateALU = HIGH;
					LD_MDR = HIGH;
				end

			// M[MAR] <- MDR
			S_16_1, S_16_2 :
				begin
					Mem_OE = HIGH;
					Mem_WE = LOW;
				end
			
			// R7 <- PC
			S_04 :
				begin
					DRMUX = IR_11;
					GatePC = HIGH;
					LD_REG = HIGH;
				end
			
			// PC <- PC + off9/off11
			S_21, S_22 :
				begin
					if(State == S_21)
						ADDR2MUX = 2'd3;
					else
						ADDR2MUX = 2'd2;

					ADDR1MUX = LOW;
					PCMUX = 2'd2;
					LD_PC = HIGH;
				end
			
			// PC <- BaseR
			S_12 :
				begin
					ADDR1MUX = HIGH;
					ADDR2MUX = 2'd0;
					SR1MUX = HIGH;
					PCMUX = 2'd2;
					LD_PC = HIGH;
				end
				
			// [BEN]
			S_00 :
				begin
					Mem_OE = HIGH;
					Mem_WE = HIGH;
				end

			// You need to finish the rest of states.....

			default : ;
		endcase
	end

	 // These should always be active
	assign Mem_CE = 1'b0;
	assign Mem_UB = 1'b0;
	assign Mem_LB = 1'b0;

endmodule
