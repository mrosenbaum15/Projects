// Inputs: Clk, Reset, Load, [15:0] D
// Outputs: [15:0] Data_Out
// Description: This module is a 16-bit shift register. It works the same way as the provided code
//					 for a 4-bit shift register, but is now extended to 16 bits.
// Purpose: A 16-bit shift register is needed for PC, MAR, MDR, and IR in the datapath.
//				This is where this module is instantiated.
module reg_16 (input  logic Clk, Reset, Load,
	      input  logic [15:0]  D, // 16-bit extension
	      output logic [15:0]  Data_Out); // 16-bit extension

    always_ff @ (posedge Clk)
    begin
	 	 if (Reset) //notice, this is a sycnrhonous reset, which is recommended on the FPGA
			  Data_Out <= 16'h0;
		 else if (Load)
			  Data_Out <= D;
    end

endmodule
