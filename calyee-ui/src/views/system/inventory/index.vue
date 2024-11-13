<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="关联的库位ID" prop="locationId">
        <el-input
          v-model="queryParams.locationId"
          placeholder="请输入关联的库位ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联的产品ID" prop="productId">
        <el-input
          v-model="queryParams.productId"
          placeholder="请输入关联的产品ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="库存数量" prop="quantity">
        <el-input
          v-model="queryParams.quantity"
          placeholder="请输入库存数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录创建用户" prop="createUser">
        <el-input
          v-model="queryParams.createUser"
          placeholder="请输入记录创建用户"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录更新用户" prop="updateUser">
        <el-input
          v-model="queryParams.updateUser"
          placeholder="请输入记录更新用户"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="删除标记，0表示未删除，1表示已删除" prop="deleteFlag">
        <el-input
          v-model="queryParams.deleteFlag"
          placeholder="请输入删除标记，0表示未删除，1表示已删除"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:inventory:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:inventory:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:inventory:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:inventory:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="inventoryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="库存记录唯一标识符" align="center" prop="inventoryId" />
      <el-table-column label="关联的库位ID" align="center" prop="locationId" />
      <el-table-column label="关联的产品ID" align="center" prop="productId" />
      <el-table-column label="库存数量" align="center" prop="quantity" />
      <el-table-column label="记录创建用户" align="center" prop="createUser" />
      <el-table-column label="记录更新用户" align="center" prop="updateUser" />
      <el-table-column label="删除标记，0表示未删除，1表示已删除" align="center" prop="deleteFlag" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:inventory:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:inventory:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改库存，记录库存量及位置信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="关联的库位ID" prop="locationId">
          <el-input v-model="form.locationId" placeholder="请输入关联的库位ID" />
        </el-form-item>
        <el-form-item label="关联的产品ID" prop="productId">
          <el-input v-model="form.productId" placeholder="请输入关联的产品ID" />
        </el-form-item>
        <el-form-item label="库存数量" prop="quantity">
          <el-input v-model="form.quantity" placeholder="请输入库存数量" />
        </el-form-item>
        <el-form-item label="记录创建用户" prop="createUser">
          <el-input v-model="form.createUser" placeholder="请输入记录创建用户" />
        </el-form-item>
        <el-form-item label="记录更新用户" prop="updateUser">
          <el-input v-model="form.updateUser" placeholder="请输入记录更新用户" />
        </el-form-item>
        <el-form-item label="删除标记，0表示未删除，1表示已删除" prop="deleteFlag">
          <el-input v-model="form.deleteFlag" placeholder="请输入删除标记，0表示未删除，1表示已删除" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listInventory, getInventory, delInventory, addInventory, updateInventory } from "@/api/system/inventory";

export default {
  name: "Inventory",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 库存，记录库存量及位置信息表格数据
      inventoryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        locationId: null,
        productId: null,
        quantity: null,
        createUser: null,
        updateUser: null,
        deleteFlag: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        locationId: [
          { required: true, message: "关联的库位ID不能为空", trigger: "blur" }
        ],
        productId: [
          { required: true, message: "关联的产品ID不能为空", trigger: "blur" }
        ],
        quantity: [
          { required: true, message: "库存数量不能为空", trigger: "blur" }
        ],
        createUser: [
          { required: true, message: "记录创建用户不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "记录创建时间不能为空", trigger: "blur" }
        ],
        deleteFlag: [
          { required: true, message: "删除标记，0表示未删除，1表示已删除不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询库存，记录库存量及位置信息列表 */
    getList() {
      this.loading = true;
      listInventory(this.queryParams).then(response => {
        this.inventoryList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        inventoryId: null,
        locationId: null,
        productId: null,
        quantity: null,
        createUser: null,
        createTime: null,
        updateUser: null,
        updateTime: null,
        deleteFlag: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.inventoryId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加库存，记录库存量及位置信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const inventoryId = row.inventoryId || this.ids
      getInventory(inventoryId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改库存，记录库存量及位置信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.inventoryId != null) {
            updateInventory(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInventory(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const inventoryIds = row.inventoryId || this.ids;
      this.$modal.confirm('是否确认删除库存，记录库存量及位置信息编号为"' + inventoryIds + '"的数据项？').then(function() {
        return delInventory(inventoryIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/inventory/export', {
        ...this.queryParams
      }, `inventory_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
