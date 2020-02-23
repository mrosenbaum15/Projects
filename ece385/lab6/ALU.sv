module ALU
(
        input logic [15:0] A,
        input logic [15:0] B,
        input logic [1:0] ALU_select,
        output logic [15:0] output_data
);

always_comb begin

        if(ALU_select == 2'b00)
            output_data = A + B;
        else if(ALU_select == 2'b01)
            output_data = A & B;
        else if(ALU_select == 2'b10)
            output_data = ~A;
        else if(ALU_select == 2'b11)
            output_data = A;

end

endmodule
