module reg_4 (input  logic Clk, Reset, Load,
              input  logic [3:0]  D,
              output logic [3:0]  Data_Out);

    always_ff @ (posedge Clk)
    begin
	 	 if (Reset) //notice, this is a sycnrhonous reset, which is recommended on the FPGA
			  Data_Out <= 4'h0;
		 else if (Load)
			  Data_Out <= D;
    end

endmodule
