module register_file
(
        input logic Clk,
        input logic Reset,
        input logic Load,
        input logic [15:0] data_bus,
        input logic [2:0] DR,
        input logic [2:0] SR1,
        input logic [2:0] SR2,
        output logic [15:0] output_SR1,
        output logic [15:0] output_SR2
);

logic [7:0][15:0] reg_system;

always_ff @ (posedge Clk) begin

        if(Load) begin

            case(DR)
                3'b000: reg_system[0] <= data_bus;
                3'b001: reg_system[1] <= data_bus;
                3'b010: reg_system[2] <= data_bus;
                3'b011: reg_system[3] <= data_bus;
                3'b100: reg_system[4] <= data_bus;
                3'b101: reg_system[5] <= data_bus;
                3'b110: reg_system[6] <= data_bus;
                3'b111: reg_system[7] <= data_bus;
            endcase

        end
        else if(Reset) begin

            for(int i = 0; i < 8; ++i)
                reg_system[i] <= 16'h0;

        end

end

always_comb begin

        case(SR1)
            3'b000: output_SR1 = reg_system[0];
            3'b001: output_SR1 = reg_system[1];
            3'b010: output_SR1 = reg_system[2];
            3'b011: output_SR1 = reg_system[3];
            3'b100: output_SR1 = reg_system[4];
            3'b101: output_SR1 = reg_system[5];
            3'b110: output_SR1 = reg_system[6];
            3'b111: output_SR1 = reg_system[7];
        endcase

        case(SR2)
            3'b000: output_SR2 = reg_system[0];
            3'b001: output_SR2 = reg_system[1];
            3'b010: output_SR2 = reg_system[2];
            3'b011: output_SR2 = reg_system[3];
            3'b100: output_SR2 = reg_system[4];
            3'b101: output_SR2 = reg_system[5];
            3'b110: output_SR2 = reg_system[6];
            3'b111: output_SR2 = reg_system[7];
        endcase

end

endmodule
