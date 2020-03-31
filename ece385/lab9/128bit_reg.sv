// Inputs: Clk, Reset, Load, [15:0] D
// Outputs: [15:0] Data_Out
// Description: This module is a 128-bit shift register. It works the same way as the provided code
//					 for a 4-bit shift register, but is now extended to 128 bits.
// Purpose: A 128-bit shift register is needed to whole important key and message encryption values
//				The module is instantiated in AES.sv
module 128bit_reg (input  logic Clk, Reset, Load1, Load2
	      input  logic [127:0]  data_1, data_2, // 128-bit extension
	      output logic [127:0]  Data_Out); // 128-bit extension

    always_ff @ (posedge Clk)
    begin
	 	 if (Reset) //notice, this is a sycnrhonous reset, which is recommended on the FPGA
			  Data_Out <= 128'h0;
		 else if (Load1)
			  Data_Out <= data_1;
         else if (Load2)
              Data_Out <= data_2;
    end

endmodule
