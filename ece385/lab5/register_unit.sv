module register_unit (input  logic Clk, Reset_A, Reset_B, A_In, B_In, Ld_A, Ld_B, 
                            Shift_En,
                      input  logic [7:0]  A_Data, 
							 input  logic [7:0]  B_Data,
                      output logic A_out, B_out, 
                      output logic [7:0]  A,
                      output logic [7:0]  B);


    reg_8  reg_A (.*, .Reset(Reset_A), .Shift_In(A_In), .Load(Ld_A),
	               .D(A_Data), .Shift_Out(A_out), .Data_Out(A));
    reg_8  reg_B (.*, .Reset(Reset_B), .Shift_In(B_In), .Load(Ld_B),
	               .D(B_Data), .Shift_Out(B_out), .Data_Out(B));

endmodule